package edu.miu.eshop.eshopadmin.security.service;

// EB

import edu.miu.eshop.eshopadmin.domain.PersonStatus;
import edu.miu.eshop.eshopadmin.security.jwt.JwtUtil;
import edu.miu.eshop.eshopadmin.security.response.PersonResponse;
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

    public PersonResponse validateToken(String token) {

        String email;
        PersonResponse personResponse = new PersonResponse();

        try {
            email = jwtUtil.getUsernameFromToken(token);
            if (email != null) {
                UserDetails userDetails = this.jwtAuthDetailService.loadUserByUsername(email);
                if (jwtUtil.validateToken(token, userDetails)) {
                    return constructTokenResponse((JwtUserDetails)userDetails, personResponse);
                }
            }
        } catch (Exception e) {
            personResponse.setStatus(PersonStatus.SUSPENDED);
        }

        return personResponse;
    }

    private PersonResponse constructTokenResponse(JwtUserDetails userDetails, PersonResponse personResponse) {
        personResponse.setPersonId(userDetails.getPersonId());
        personResponse.setStatus(PersonStatus.ACTIVE);
        personResponse.setUsername(userDetails.getUsername());
        personResponse.setRole(userDetails.getRole());

        return personResponse;
    }
}
