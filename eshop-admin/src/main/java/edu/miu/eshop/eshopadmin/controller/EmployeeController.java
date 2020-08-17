package edu.miu.eshop.eshopadmin.controller;

// EB

import edu.miu.eshop.eshopadmin.domain.Dto.EmployeeDto;
import edu.miu.eshop.eshopadmin.domain.Dto.PasswordDto;
import edu.miu.eshop.eshopadmin.domain.Employee;
import edu.miu.eshop.eshopadmin.exception.CustomerNotFoundException;
import edu.miu.eshop.eshopadmin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmployeeDto> getAllEmployee(){

        return employeeService.getAll().stream().map(EmployeeDto::build).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeDto addEmployee(@RequestBody Employee newEmployee){
        return employeeService.addNewEmployee(newEmployee);
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto findEmployeeById(@PathVariable("employeeId") String employeeId){
        try {
            return EmployeeDto.build(employeeService.findById(employeeId));
        } catch (CustomerNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Employee ID not found!", ex);
        }
    }

    @GetMapping("/engineers")
    public List<Employee> getEngineers(){
        return employeeService.getEngineers();
    }

    @PutMapping("/{employeeId}")
    public EmployeeDto updateEmployee(@PathVariable("employeeId") String employeeId, @RequestBody EmployeeDto newEmployee){
        return EmployeeDto.build(employeeService.update(employeeId, newEmployee));
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") String employeeId) {
        employeeService.deleteById(employeeId);
    }


    @PatchMapping("/{employeeId}/reset")
    private void resetPassword(@PathVariable String employeeId, @RequestBody PasswordDto passwordDto){
        employeeService.resetPassword(employeeId, passwordDto.getPassword());
    }

///users/{}/reset https://www.baeldung.com/spring-security-registration-i-forgot-my-password
}
