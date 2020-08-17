package edu.miu.eshop.eshopadmin.domain.Dto;

// EB

import edu.miu.eshop.eshopadmin.domain.Person;
import edu.miu.eshop.eshopadmin.domain.PersonStatus;
import edu.miu.eshop.eshopadmin.domain.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDto {
    private String personId;
    private String username;
    private String phone;
    private PersonStatus status;
    private String imageUrl;
    private AddressDto address;
    private Role role;

    public static PersonDto build(Person person) {
        return new PersonDto(
                person.getPersonId(),
                person.getUsername(),
                person.getPhone(),
                person.getStatus(),
                person.getImageUrl(),
                person.getAddress(),
                person.getRole());
    }

}
