package edu.miu.eshop.eshopuser.controller;

import edu.miu.eshop.eshopuser.domain.Customer;
import edu.miu.eshop.eshopuser.domain.CustomerScoreDto;
import edu.miu.eshop.eshopuser.domain.Dto.BankCardDto;
import edu.miu.eshop.eshopuser.domain.Dto.CustomerDto;
import edu.miu.eshop.eshopuser.domain.Dto.PasswordDto;
import edu.miu.eshop.eshopuser.exception.CustomerNotFoundException;
import edu.miu.eshop.eshopuser.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/users")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerDto> getAllCustomer(){
        return customerService.getAll().stream().map(CustomerDto::build).collect(Collectors.toList());
    }

    @GetMapping("/{customerId}")
    public CustomerDto findCustomerById(@PathVariable("customerId") String customerId){
        try {
            return CustomerDto.build(customerService.findById(customerId));
        } catch (CustomerNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Customer ID not found!", ex);
        }
    }

//    @GetMapping("/{username}")
//    public Customer findCustomerByUsername(@PathVariable("username") String customerId){
//        try {
//            return customerService.findById(customerId);
//        } catch (CustomerNotFoundException ex) {
//            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Customer username not found!", ex);
//        }
//    }
    // -- Related to the BANK CARD --
    @GetMapping("/cards/{customerId}")
    public Set<BankCardDto> getCards(@PathVariable("customerId") String customerId){
        return customerService.findById(customerId).getCards();
    }

    @PostMapping("/cards/{customerId}")
    public void addCard(@PathVariable("customerId") String customerId, @RequestBody BankCardDto bankCardDto){
        try {
            Customer customer = customerService.findById(customerId);
            customer.addCard(bankCardDto);
            customerService.save(customer);
        }catch(CustomerNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Customer not found!", ex);
        }
    }

    @DeleteMapping("/cards/{customerId}")
    public void removeCard(@PathVariable("customerId") String customerId, @RequestBody BankCardDto bankCardDto){
        customerService.removeCard(customerId, bankCardDto);
    }
    // -- End of BANK CARD --

    @PutMapping("/{customerId}")
    public CustomerDto updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto newCustomer){
        return CustomerDto.build(customerService.update(customerId, newCustomer));
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") String customerId) {
        customerService.deleteById(customerId);
    }

    @GetMapping("/{customerId}/score")
    public CustomerScoreDto getScore(@PathVariable("customerId") String customerId){
        Customer customer = customerService.findById(customerId);
        return CustomerScoreDto.build(customer);
    }

    @GetMapping("/{customerId}/score/{score}")
    private void addScore(@PathVariable String customerId, @PathVariable double score){
            customerService.addScore(customerId, score);
    }

    @PatchMapping("/{customerId}/reset")
    private void resetPassword(@PathVariable String customerId, @RequestBody PasswordDto passwordDto){
        customerService.resetPassword(customerId, passwordDto.getPassword());
    }

///users/{}/reset https://www.baeldung.com/spring-security-registration-i-forgot-my-password
}
