package ru.elagin.hostel.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.elagin.hostel.entities.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class SecurityUser implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean status;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean status) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

    public static UserDetails fromUserDetails(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getStatus().equals("active"),
                user.getStatus().equals("active"),
                user.getStatus().equals("active"),
                user.getStatus().equals("active"),
                authorities
        );
    }
}
