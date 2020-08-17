package edu.miu.eshop.eshopadmin.domain.Dto;

// EB

import edu.miu.eshop.eshopadmin.domain.Employee;
import edu.miu.eshop.eshopadmin.domain.PersonStatus;
import edu.miu.eshop.eshopadmin.domain.Role;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {
    private String employeeId;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private PersonStatus status;
    private Role role;
    private String imageUrl;
    private AddressDto address;
    private LocalDate createdDate;

    public static EmployeeDto build(Employee employee) {
        return new EmployeeDto(
                employee.getPersonId(),
                employee.getUsername(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getPhone(),
                employee.getStatus(),
                employee.getRole(),
                employee.getImageUrl(),
                employee.getAddress(),
                employee.getCreatedDate());
    }

}