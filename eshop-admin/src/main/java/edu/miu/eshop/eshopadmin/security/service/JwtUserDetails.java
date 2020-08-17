package edu.miu.eshop.eshopadmin.security.service;

// EB

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.eshop.eshopadmin.domain.Person;
import edu.miu.eshop.eshopadmin.domain.PersonStatus;
import edu.miu.eshop.eshopadmin.domain.Role;
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
    private final String personId;
    private final String username;
    private final String password;
    private final Role role;
    private final PersonStatus status;

    public JwtUserDetails(String personId, String username, String password, Role role, PersonStatus status) {
        this.personId = personId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public static JwtUserDetails build(Person person) {
        return new JwtUserDetails(
                person.getPersonId(),
                person.getUsername(),
                person.getPassword(),
                person.getRole(),
                person.getStatus());
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
        return this.status != PersonStatus.SUSPENDED;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status != PersonStatus.DELETED;
    }
    public Role getRole() {
        return role;
    }
}
