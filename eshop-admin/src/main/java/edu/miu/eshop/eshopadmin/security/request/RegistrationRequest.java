package edu.miu.eshop.eshopadmin.security.request;

// EB

import edu.miu.eshop.eshopadmin.domain.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistrationRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private Role role;
}
