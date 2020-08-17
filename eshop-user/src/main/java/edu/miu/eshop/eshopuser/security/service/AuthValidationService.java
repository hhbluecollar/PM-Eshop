package edu.miu.eshop.eshopuser.security.service;

import edu.miu.eshop.eshopuser.security.response.CustomerResponse;

public interface AuthValidationService {
    CustomerResponse validateToken(String token);
}
