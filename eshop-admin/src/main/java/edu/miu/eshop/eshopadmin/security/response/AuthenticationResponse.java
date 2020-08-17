package edu.miu.eshop.eshopadmin.security.response;

// EB

import edu.miu.eshop.eshopadmin.domain.Person;
import edu.miu.eshop.eshopadmin.domain.PersonStatus;
import edu.miu.eshop.eshopadmin.domain.Role;
import jdk.jfr.SettingDefinition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String type = "Bearer";
    private String userId;
    private String username;
    private Role role;
    private PersonStatus status;
    public static AuthenticationResponse build(String token, Person person){
        return new AuthenticationResponse(
            token,
                "Bearer",
            person.getPersonId(),
            person.getUsername(),
            person.getRole(),
            person.getStatus()
        );
    }
}
