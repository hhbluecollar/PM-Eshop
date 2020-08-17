package edu.miu.eshop.eshopuser.security.service;

import edu.miu.eshop.eshopuser.domain.CustomerStatus;
import edu.miu.eshop.eshopuser.security.jwt.JwtUtil;
import edu.miu.eshop.eshopuser.security.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    @Autowired
    @Qualifier("JwtAuthDetailsService")
    private JwtAuthDetailService jwtAuthDetailService;

    @Autowired
    JwtUtil jwtUtil;

    public CustomerResponse validateToken(String token) {

        String email;
        CustomerResponse customerResponse = new CustomerResponse();

        try {
            email = jwtUtil.getUsernameFromToken(token);
            if (email != null) {
                UserDetails userDetails = this.jwtAuthDetailService.loadUserByUsername(email);
                if (jwtUtil.validateToken(token, userDetails)) {
                    return constructTokenResponse((JwtUserDetails)userDetails, customerResponse);
                }
            }
        } catch (Exception e) {
            customerResponse.setStatus(CustomerStatus.SUSPENDED);
        }

        return customerResponse;
    }

    private CustomerResponse constructTokenResponse(JwtUserDetails userDetails, CustomerResponse customerResponse) {
        customerResponse.setCustomerId(userDetails.getCustomerId());
        customerResponse.setStatus(CustomerStatus.ACTIVE);
        customerResponse.setUsername(userDetails.getUsername());
        customerResponse.setRole(userDetails.getRole());

        return customerResponse;
    }
}
