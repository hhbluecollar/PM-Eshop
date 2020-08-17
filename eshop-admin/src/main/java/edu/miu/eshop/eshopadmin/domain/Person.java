package edu.miu.eshop.eshopadmin.domain;

// EB

import edu.miu.eshop.eshopadmin.domain.Dto.AddressDto;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Document(collection = "person")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Person {
    @Id
    private String id;
    @Indexed(unique=true)
    private String personId;
    @Indexed(unique=true)
    private String username;
    private String password;
    private LocalDate createdDate;
    private String phone;
    private PersonStatus status = PersonStatus.ACTIVE;
    private Role role = Role.ROLE_VENDOR;
    private String imageUrl;
    private AddressDto address;
}
