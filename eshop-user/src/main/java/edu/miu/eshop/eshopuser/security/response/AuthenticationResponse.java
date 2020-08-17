package edu.miu.eshop.eshopuser.security.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String type = "Bearer";
    private String userId;
    private String username;
    public AuthenticationResponse(String token, String userId, String username){
        this.token = token;
        this.userId = userId;
        this.username = username;
    }
}
