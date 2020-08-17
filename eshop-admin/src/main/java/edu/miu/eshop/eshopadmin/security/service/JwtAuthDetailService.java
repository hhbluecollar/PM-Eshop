package edu.miu.eshop.eshopadmin.security.service;

// EB

import edu.miu.eshop.eshopadmin.domain.Person;
import edu.miu.eshop.eshopadmin.service.PersonService;
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
    private PersonService personService;

    @Transactional
    public UserDetails loadUserByUsername(String username) {
        try {
            Person person = personService.findByUsername(username);
            return JwtUserDetails.build(person);
        }catch(UsernameNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Customer not found!", ex);
        }
    }

}
