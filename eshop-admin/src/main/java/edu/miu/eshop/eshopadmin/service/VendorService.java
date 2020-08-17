package edu.miu.eshop.eshopadmin.service;

// EB

import edu.miu.eshop.eshopadmin.domain.Dto.BankCardDto;
import edu.miu.eshop.eshopadmin.domain.Dto.BooleanDto;
import edu.miu.eshop.eshopadmin.domain.Dto.OrderItemDto;
import edu.miu.eshop.eshopadmin.domain.Dto.VendorDto;
import edu.miu.eshop.eshopadmin.domain.Vendor;
import edu.miu.eshop.eshopadmin.exception.EmailAlreadyExistException;
import edu.miu.eshop.eshopadmin.security.request.RegistrationRequest;
import edu.miu.eshop.eshopadmin.security.response.PersonResponse;

import java.util.List;

public interface VendorService {

    List<Vendor> getAll();

    Vendor findById(String vendorId);

    Vendor save(Vendor newVendor);

    void deleteById(String vendorId);

    void suspendById(String vendorId);

    Vendor findByUsername(String username);

    PersonResponse saveNew(RegistrationRequest registrationRequest) throws EmailAlreadyExistException;

    Vendor update(String vendorId, VendorDto newPerson);

    void removeCard(String vendorId, BankCardDto bankCardDto);

    void resetPassword(String vendorId, String password);

    BooleanDto oneTimePayment(String vendorId, BankCardDto bankCard);

    List<OrderItemDto> getOrderByVendor(String vendorId);
}
