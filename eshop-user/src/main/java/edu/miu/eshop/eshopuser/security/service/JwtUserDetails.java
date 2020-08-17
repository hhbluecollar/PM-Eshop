package edu.miu.eshop.eshopuser.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.eshop.eshopuser.domain.Customer;
import edu.miu.eshop.eshopuser.domain.CustomerStatus;
import edu.miu.eshop.eshopuser.domain.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {
    private final String customerId;
    private final String username;
    private final String password;
    private final Role role;
    private final CustomerStatus status;

    public JwtUserDetails(String customerId, String username, String password, Role role, CustomerStatus status) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public static JwtUserDetails build(Customer customer) {
        return new JwtUserDetails(
                customer.getCustomerId(),
                customer.getUsername(),
                customer.getPassword(),
                customer.getRole(),
                customer.getStatus());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.status != CustomerStatus.SUSPENDED;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status != CustomerStatus.DELETED;
    }
    public Role getRole() {
        return role;
    }
}
