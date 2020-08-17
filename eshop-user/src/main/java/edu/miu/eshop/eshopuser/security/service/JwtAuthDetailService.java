package edu.miu.eshop.eshopuser.security.service;

import edu.miu.eshop.eshopuser.domain.Customer;
import edu.miu.eshop.eshopuser.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service("JwtAuthDetailsService")
public class JwtAuthDetailService implements UserDetailsService {
    @Autowired
    private CustomerService customerService;

    @Transactional
    public UserDetails loadUserByUsername(String username) {
        try {
            Customer customer = customerService.findByUsername(username);
            return JwtUserDetails.build(customer);
        }catch(UsernameNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Customer not found!", ex);
        }
    }

}
