package edu.miu.eshop.eshopadmin.security.response;

// EB

import edu.miu.eshop.eshopadmin.domain.PersonStatus;
import edu.miu.eshop.eshopadmin.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {
    private String personId;
    private String username;
    private Role role;
    private PersonStatus status;
}
