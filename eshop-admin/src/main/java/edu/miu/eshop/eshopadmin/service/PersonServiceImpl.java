package edu.miu.eshop.eshopadmin.service;

// EB

import edu.miu.eshop.eshopadmin.domain.Person;
import edu.miu.eshop.eshopadmin.domain.Role;
import edu.miu.eshop.eshopadmin.exception.CustomerNotFoundException;
import edu.miu.eshop.eshopadmin.exception.EmailAlreadyExistException;
import edu.miu.eshop.eshopadmin.security.request.RegistrationRequest;
import edu.miu.eshop.eshopadmin.security.response.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private final VendorService vendorService;
    private final EmployeeService employeeService;

    @Autowired
    public PersonServiceImpl(@Qualifier("VendorService") VendorService vendorService, @Qualifier("EmployeeService") EmployeeService employeeService) {
        this.vendorService = vendorService;
        this.employeeService = employeeService;
    }

    @Override
    public Person findById(String id) {
        Person vendor = vendorService.findById(id);
        Person employee = employeeService.findById(id);
        if(vendor == null && employee == null)
            throw new CustomerNotFoundException(id);
        return vendor == null ? employee : vendor;
    }

    @Override
    public Person findByUsername(String username) {
        Person vendor = vendorService.findByUsername(username);
        Person employee = employeeService.findByUsername(username);
        if(vendor.equals(null) && employee.equals(null))
            throw new CustomerNotFoundException(username);
        return vendor == null ? employee : vendor;
    }

    @Override
    public PersonResponse saveNew(RegistrationRequest registrationRequest) throws EmailAlreadyExistException {
        PersonResponse personResponse;
        if(registrationRequest.getRole().equals(Role.ROLE_VENDOR))
            personResponse = vendorService.saveNew(registrationRequest);
        else
            personResponse = employeeService.saveNew(registrationRequest);
        return personResponse;
    }
}
