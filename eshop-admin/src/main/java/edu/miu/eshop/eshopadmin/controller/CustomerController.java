package edu.miu.eshop.eshopadmin.controller;

// EB

import edu.miu.eshop.eshopadmin.domain.Dto.CustomerDto;
import edu.miu.eshop.eshopadmin.exception.CustomerNotFoundException;
import edu.miu.eshop.eshopadmin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/users")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<CustomerDto> getAllCustomer() throws IOException {
		return customerService.getAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{customerId}")
	public CustomerDto findCustomerById(@PathVariable("customerId") String customerId){
		try {
			return customerService.findById(customerId);
		} catch (CustomerNotFoundException | IOException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Customer ID not found!", ex);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{customerId}")
	public CustomerDto updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto newCustomer){
		return customerService.update(customerId, newCustomer);
	}
}