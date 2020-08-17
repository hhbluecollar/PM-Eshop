package edu.miu.eshop.eshopadmin.security.request;

// EB

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
