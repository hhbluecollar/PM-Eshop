package edu.miu.eshop.eshopadmin.service;

// EB

import edu.miu.eshop.eshopadmin.domain.*;
import edu.miu.eshop.eshopadmin.domain.Dto.*;
import edu.miu.eshop.eshopadmin.exception.CustomerNotFoundException;
import edu.miu.eshop.eshopadmin.exception.EmailAlreadyExistException;
import edu.miu.eshop.eshopadmin.repository.VendorRepository;
import edu.miu.eshop.eshopadmin.security.request.RegistrationRequest;
import edu.miu.eshop.eshopadmin.security.response.PersonResponse;
import edu.miu.eshop.eshopadmin.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Transactional
@Service("VendorService")
public class VendorServiceImpl implements VendorService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final VendorRepository vendorRepository;

    private final MongoTemplate mongoTemplate;

    private final RestTemplate restTemplate;

    HttpHeaders headers;
    Properties prop;
    InputStream inputStream;
    String propFileName = "application.properties";

    @PostConstruct
    private void initProperty() throws IOException {

        prop = new Properties();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            try {
                throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, BCryptPasswordEncoder passwordEncoder, MongoTemplate mongoTemplate, RestTemplate restTemplate) {
        this.vendorRepository = vendorRepository;
        this.passwordEncoder = passwordEncoder;
        this.mongoTemplate = mongoTemplate;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Vendor> getAll() {
        return vendorRepository.findAll().stream()
                .filter(v -> !v.getStatus().equals(PersonStatus.DELETED))
                .collect(Collectors.toList());
    }

    @Override
    public Vendor findById(String customerId) {
        return vendorRepository.findByPersonId(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    public Vendor save(Vendor newCustomer) {
        return vendorRepository.save(newCustomer);
    }

    @Override
    public void deleteById(String vendorId) {
        vendorRepository.findByPersonId(vendorId).map(vendor -> {
            vendor.setStatus(PersonStatus.DELETED);
            return vendorRepository.save(vendor);
        });
    }
    @Override
    public void suspendById(String vendorId) {
        vendorRepository.findByPersonId(vendorId).map(vendor -> {
            vendor.setStatus(PersonStatus.SUSPENDED);
            return vendorRepository.save(vendor);
        });
    }

    @Override
    public Vendor findByUsername(String username) {
        return vendorRepository.findByUsername(username).orElseThrow(() -> new CustomerNotFoundException(username));
    }

    @Override
    public PersonResponse saveNew(RegistrationRequest registrationRequest) throws EmailAlreadyExistException {
        isUserNameExists(registrationRequest.getUsername());
        Vendor newVendor = new Vendor();
        newVendor.setUsername(registrationRequest.getUsername());
        newVendor.setPassword(encodePassword(registrationRequest.getPassword()));
        newVendor.setCreatedDate(LocalDate.now());
        newVendor.setRole(Role.ROLE_VENDOR);
        newVendor.setStatus(PersonStatus.NEW);
        newVendor.setPersonId(IdGenerator.getTimeStamp("VE"));
        vendorRepository.save(newVendor);
        Vendor addedVendor = vendorRepository.findByUsername(newVendor.getUsername()).get();
        PersonResponse personResponse = new PersonResponse();
        personResponse.setPersonId(addedVendor.getPersonId());
        personResponse.setUsername(addedVendor.getUsername());
        personResponse.setRole(addedVendor.getRole());
        personResponse.setStatus(PersonStatus.ACTIVE);
        return personResponse;
    }

    @Override
    public Vendor update(String vendorId, VendorDto newVendor) {
        Vendor vendor = findById(vendorId);
        vendor.setVendorName(newVendor.getVendorName());
        vendor.setPhone(newVendor.getPhone());
        vendor.setCards(newVendor.getCards());
        vendor.setStatus(newVendor.getStatus());
        vendor.setImageUrl(newVendor.getImageUrl());
        vendor.setAddress(newVendor.getAddress());
        vendor.setDescription(newVendor.getDescription());
        vendor.setContactMethod(newVendor.getContactMethod());
        return vendorRepository.save(vendor);
    }

    @Override
    public void removeCard(String vendorId, BankCardDto bankCardDto) {
        Query query = new Query(Criteria.where("personId").is(vendorId));
        Update update = new Update().pull("cards", bankCardDto);
        mongoTemplate.update(Vendor.class)
                .matching(query)
                .apply(update)
                .findAndModifyValue();
    }

    @Override
    public void resetPassword(String vendorId, String password) {
        Vendor vendor = findById(vendorId);
        vendor.setPassword(encodePassword(password));
        save(vendor);
    }

    @Override
    public BooleanDto oneTimePayment(String vendorId, BankCardDto bankCard) {
        double amount = Double.parseDouble(prop.getProperty("onetimePaymentAmount"));
        Vendor vendor = findById(vendorId);
        HttpHeaders headersPay = new HttpHeaders();
        headersPay.setContentType(MediaType.APPLICATION_JSON);

        String urlPay =  prop.getProperty("url.payment.service") + prop.getProperty("url.payment.pay");
        HttpEntity paymentEntity = new HttpEntity(new Transaction(Card.build(bankCard, vendor.getAddress()), amount), headersPay);
        BooleanDto result = restTemplate.exchange(urlPay,
                HttpMethod.POST,
                paymentEntity,
                BooleanDto.class).getBody();
        if(result.isBool()) {
            vendor.setStatus(PersonStatus.ACTIVE);
            vendorRepository.save(vendor);
        }
        return result;
    }

    @Override
    public List<OrderItemDto> getOrderByVendor(String vendorId) {
        ResponseEntity<List<OrderItemDto>> response = restTemplate.exchange(
                prop.getProperty("eshop.product.url.base") + "/orders/orderitems/" + vendorId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderItemDto>>(){});
        return response.getBody();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void isUserNameExists(String username) throws EmailAlreadyExistException {
        if (vendorRepository.existsByUsername(username))
            throw new EmailAlreadyExistException("Email is already registered!");
    }
}
