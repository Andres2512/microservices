package com.microservice.serviceoauth.services;

import com.microservice.commonsuser.models.entity.User;
import com.microservice.serviceoauth.client.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserFeignClient client;

    public UserService(UserFeignClient client) {
        this.client = client;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = client.findByUsername(s);
        if (user == null){
            log.error("Error en el login, no existe el usuario: '"+s+"'en el sistema");
            throw new UsernameNotFoundException("Error en el login, no existe el usuario: '"+s+"'en el sistema");
        }
        List<GrantedAuthority> authorities= user.getRoles().stream()
                .map(role-> new SimpleGrantedAuthority(role.getDescription()))
                .peek(authority-> log.error("Role: " +authority.getAuthority()))
                .collect(Collectors.toList());
        log.info("usuario " + s);


        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getEnabled()
        ,true,true,true,authorities);
    }
}
