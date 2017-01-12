package cz.fi.muni.pa165.service.security;

import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rtrembecky
 */
@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
            final User user = userService.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with email: " + email);
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(),
                    true, true, true, true, getAuthorities(user.getRole()));
    }

    private List<GrantedAuthority> getAuthorities(Role role) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        switch (role) {
            case USER:
                authorities.add(new SimpleGrantedAuthority(Role.USER.getAuthority()));
                break;
            case MANAGER:
                authorities.add(new SimpleGrantedAuthority(Role.USER.getAuthority()));
            case ADMIN:
                authorities.add(new SimpleGrantedAuthority(Role.USER.getAuthority()));
                authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getAuthority()));
                break;
        }
        return authorities;
    }
}
