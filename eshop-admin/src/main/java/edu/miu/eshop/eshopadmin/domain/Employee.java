package edu.miu.eshop.eshopadmin.domain;

// EB

import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
@TypeAlias("employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee extends Person{
    private String firstName;
    private String lastName;
}
