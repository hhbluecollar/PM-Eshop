package edu.miu.eshop.eshopadmin.controller;

// EB

import edu.miu.eshop.eshopadmin.domain.Person;
import edu.miu.eshop.eshopadmin.exception.AuthenticationException;
import edu.miu.eshop.eshopadmin.exception.EmailAlreadyExistException;
import edu.miu.eshop.eshopadmin.exception.JwtTokenException;
import edu.miu.eshop.eshopadmin.security.jwt.JwtUtil;
import edu.miu.eshop.eshopadmin.security.request.AuthenticationRequest;
import edu.miu.eshop.eshopadmin.security.request.RegistrationRequest;
import edu.miu.eshop.eshopadmin.security.response.AuthenticationResponse;
import edu.miu.eshop.eshopadmin.security.response.PersonResponse;
import edu.miu.eshop.eshopadmin.security.service.AuthValidationService;
import edu.miu.eshop.eshopadmin.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
public class AuthenticationController {

    private final PersonService personService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private AuthValidationService authValidationService;

    @Autowired
    public AuthenticationController(PersonService personService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, AuthValidationService authValidationService) {
        this.personService = personService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.authValidationService = authValidationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> authenticateCustomer(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwt = jwtUtil.generateJwtToken(authentication);
        Person person = personService.findByUsername(authenticationRequest.getUsername());
        return ResponseEntity.ok(AuthenticationResponse.build(jwt, person));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public PersonResponse registerCustomer(@RequestBody RegistrationRequest registrationRequest) {
        try {
            return personService.saveNew(registrationRequest);
        } catch (EmailAlreadyExistException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Email is already registered!", ex);
        }
    }

    @PostMapping(value = "/validate")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public PersonResponse validateToken(@RequestBody String token) {
        return authValidationService.validateToken(token);
    }

    @GetMapping(value = "/validate")
    public PersonResponse validateToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new JwtTokenException("No JWT token found in request headers.");
        }
        String token = header.substring(7);
        return authValidationService.validateToken(token);
    }

    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException exp) {
            throw new AuthenticationException("INVALID_CREDENTIALS", exp);
        } catch (LockedException exp) {
            throw new AuthenticationException("LOCKED_USER", exp);
        } catch (DisabledException exp) {
            throw new AuthenticationException("USER_NOT_EXISTING", exp);
        }
    }
}