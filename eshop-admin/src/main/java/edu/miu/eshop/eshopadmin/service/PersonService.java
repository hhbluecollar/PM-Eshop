package edu.miu.eshop.eshopadmin.service;

// EB

import edu.miu.eshop.eshopadmin.domain.Person;
import edu.miu.eshop.eshopadmin.exception.EmailAlreadyExistException;
import edu.miu.eshop.eshopadmin.security.request.RegistrationRequest;
import edu.miu.eshop.eshopadmin.security.response.PersonResponse;

public interface PersonService {

    Person findById(String id);

    Person findByUsername(String username);

    PersonResponse saveNew(RegistrationRequest registrationRequest) throws EmailAlreadyExistException;

}
