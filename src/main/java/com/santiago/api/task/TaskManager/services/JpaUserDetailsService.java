package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.User;
import com.santiago.api.task.TaskManager.repositories.UserRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepostory repostory;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> userOptional = repostory.findUsername(name);
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User %s not found", name));
        }
        User user = userOptional.orElseThrow();

        // Lista de roles genéricos
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role->new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                true,  // isEnabled (marcado como true porque no se maneja en tu entidad)
                true,  // accountNonExpired
                true,  // credentialsNonExpired
                true,  // accountNonLocked
                authorities
        );
    }
}
