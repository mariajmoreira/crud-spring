package com.crud.crudspring.config;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.crud.crudspring.model.MyUserDetails;
import com.crud.crudspring.model.User;
import com.crud.crudspring.repository.UsersRepository;
 
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UsersRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        GrantedAuthority ga = new SimpleGrantedAuthority(user.getRole());
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(ga);
         
       // return new MyUserDetails(user);
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }
 
}
