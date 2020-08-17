package edu.miu.eshop.eshopuser.security.request;

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
