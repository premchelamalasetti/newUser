package com.userapi.config;


import com.userapi.model.UserRole;
import com.userapi.repository.UserRepository;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        com.userapi.model.User user=userRepository.findByName(firstName).orElseThrow(() -> new  UsernameNotFoundException("UserName not found"));
        return new User(user.getFirstName(), user.getPassword(), mapRoleAuthorities(user.getRoles()));
    }
    private Collection<GrantedAuthority> mapRoleAuthorities(List<UserRole> roles)
    {
        return roles.stream().map(role ->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
