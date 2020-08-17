package edu.miu.eshop.eshopadmin.service;

// EB

import edu.miu.eshop.eshopadmin.domain.Dto.EmployeeDto;
import edu.miu.eshop.eshopadmin.domain.Employee;
import edu.miu.eshop.eshopadmin.exception.EmailAlreadyExistException;
import edu.miu.eshop.eshopadmin.security.request.RegistrationRequest;
import edu.miu.eshop.eshopadmin.security.response.PersonResponse;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee findById(String EmployeeId);

    Employee save(Employee newEmployee);

    void deleteById(String EmployeeId);

    void suspendById(String EmployeeId);

    Employee findByUsername(String username);

    PersonResponse saveNew(RegistrationRequest registrationRequest) throws EmailAlreadyExistException;

    Employee update(String EmployeeId, EmployeeDto newEmployee);

    void resetPassword(String EmployeeId, String password);

    List<Employee> getEngineers();

    EmployeeDto addNewEmployee(Employee newEmployee);
}
