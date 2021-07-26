package ru.elagin.hostel.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elagin.hostel.entities.Role;
import ru.elagin.hostel.entities.User;
import ru.elagin.hostel.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByLogin(username);

        if (!user.isPresent()) throw new UsernameNotFoundException("User " + username + " is don't exist");

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.get().getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.get().getLogin(), user.get().getPassword(), grantedAuthorities);
    }

}


