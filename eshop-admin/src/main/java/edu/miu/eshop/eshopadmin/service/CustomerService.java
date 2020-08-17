package edu.miu.eshop.eshopadmin.service;

// HH

import edu.miu.eshop.eshopadmin.domain.Category;
import edu.miu.eshop.eshopadmin.domain.Customer;
import edu.miu.eshop.eshopadmin.domain.Dto.CategoryDto;
import edu.miu.eshop.eshopadmin.domain.Dto.CustomerDto;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public interface CustomerService {
	List<CustomerDto> getAll() throws IOException;

	CustomerDto findById(String customerId) throws IOException;

	CustomerDto update(String customerId, CustomerDto newCustomer);
}