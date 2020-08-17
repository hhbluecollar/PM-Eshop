package edu.miu.eshop.eshopadmin.service;

// EB

import edu.miu.eshop.eshopadmin.domain.Dto.EmployeeDto;
import edu.miu.eshop.eshopadmin.domain.Employee;
import edu.miu.eshop.eshopadmin.domain.PersonStatus;
import edu.miu.eshop.eshopadmin.domain.Role;
import edu.miu.eshop.eshopadmin.exception.CustomerNotFoundException;
import edu.miu.eshop.eshopadmin.exception.EmailAlreadyExistException;
import edu.miu.eshop.eshopadmin.repository.EmployeeRepository;
import edu.miu.eshop.eshopadmin.security.request.RegistrationRequest;
import edu.miu.eshop.eshopadmin.security.response.PersonResponse;
import edu.miu.eshop.eshopadmin.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final EmployeeRepository employeeRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BCryptPasswordEncoder passwordEncoder, MongoTemplate mongoTemplate) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(String employeeId) {
        return employeeRepository.findByPersonId(employeeId).orElseThrow(() -> new CustomerNotFoundException(employeeId));
    }

    @Override
    public Employee save(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @Override
    public void deleteById(String employeeId) {
        employeeRepository.findByPersonId(employeeId).map(customer -> {
            customer.setStatus(PersonStatus.DELETED);
            return employeeRepository.save(customer);
        });
    }
    @Override
    public void suspendById(String employeeId) {
        employeeRepository.findByPersonId(employeeId).map(customer -> {
            customer.setStatus(PersonStatus.SUSPENDED);
            return employeeRepository.save(customer);
        });
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username).orElseThrow(() -> new CustomerNotFoundException(username));
    }

    @Override
    public PersonResponse saveNew(RegistrationRequest registrationRequest) throws EmailAlreadyExistException {
        isUserNameExists(registrationRequest.getUsername());
        Employee newEmployee = new Employee();
        newEmployee.setUsername(registrationRequest.getUsername());
        newEmployee.setPassword(encodePassword(registrationRequest.getPassword()));
        newEmployee.setCreatedDate(LocalDate.now());
        newEmployee.setRole(registrationRequest.getRole());
        newEmployee.setPersonId(IdGenerator.getTimeStamp("EM"));
        employeeRepository.save(newEmployee);
        Employee addedEmployee = employeeRepository.findByUsername(newEmployee.getUsername()).get();
        PersonResponse personResponse = new PersonResponse();
        personResponse.setPersonId(addedEmployee.getPersonId());
        personResponse.setUsername(addedEmployee.getUsername());
        personResponse.setRole(addedEmployee.getRole());
        personResponse.setStatus(addedEmployee.getStatus());
        return personResponse;
    }

    @Override
    public Employee update(String employeeId, EmployeeDto newEmployee) {
        Employee employee = findById(employeeId);
        employee.setFirstName(newEmployee.getFirstName());
        employee.setLastName(newEmployee.getLastName());
        employee.setPhone(newEmployee.getPhone());
        employee.setStatus(newEmployee.getStatus());
        employee.setRole(newEmployee.getRole());
        employee.setImageUrl(newEmployee.getImageUrl());
        employee.setAddress(newEmployee.getAddress());
        return employeeRepository.save(employee);
    }

    @Override
    public void resetPassword(String employeeId, String password) {
        Employee employee = findById(employeeId);
        employee.setPassword(encodePassword(password));
        save(employee);
    }

    @Override
    public List<Employee> getEngineers() {
        return employeeRepository.findByRoleQuery(Role.ROLE_ENGINEER);
    }

    @Override
    public EmployeeDto addNewEmployee(Employee newEmployee) {
        newEmployee.setPassword(encodePassword(newEmployee.getPassword()));
        newEmployee.setPersonId(IdGenerator.getTimeStamp("EM"));
        newEmployee.setCreatedDate(LocalDate.now());
        return EmployeeDto.build(employeeRepository.save(newEmployee));
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void isUserNameExists(String username) throws EmailAlreadyExistException {
        if (employeeRepository.existsByUsername(username))
            throw new EmailAlreadyExistException("Email is already registered!");
    }
}
