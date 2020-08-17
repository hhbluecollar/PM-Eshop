package edu.miu.eshop.eshopuser.security.response;

import edu.miu.eshop.eshopuser.domain.CustomerStatus;
import edu.miu.eshop.eshopuser.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String customerId;
    private String username;
    private Role role;
    private CustomerStatus status;
}
