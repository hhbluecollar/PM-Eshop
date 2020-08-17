package edu.miu.eshop.eshopadmin.security.service;

// EB

import edu.miu.eshop.eshopadmin.security.response.PersonResponse;

public interface AuthValidationService {
    PersonResponse validateToken(String token);
}
