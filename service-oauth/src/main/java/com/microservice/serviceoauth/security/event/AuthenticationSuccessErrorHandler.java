package com.microservice.serviceoauth.security.event;

import com.microservice.commonsuser.models.entity.User;
import com.microservice.serviceoauth.services.IUserService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    private final IUserService userService;

    public AuthenticationSuccessErrorHandler(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String message = "Success Login: " + userDetails.getUsername();
        log.info(message);
        System.out.println(message);

        User user = userService.findByUserName(authentication.getName());
        if (user.getIntent() != null && user.getIntent() > 0) {
            user.setIntent(0);
            userService.update(user.getId(), user);
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        String message = "Error en el login " + e.getMessage();
        log.error(message);
        System.out.println(message);

        try {
            User user = userService.findByUserName(authentication.getName());
            if (user.getIntent() == null) {
                user.setIntent(0);
            }
            log.info("Intentos actual es de " + user.getIntent());
            user.setIntent(user.getIntent() + 1);
            log.info("Intentos despues de " + user.getIntent());
            if (user.getIntent() >= 3) {
                log.error(String.format("Deshabilitado por maximo de intentos ", user.getName()));
                user.setEnabled(false);
            }
            userService.update(user.getId(), user);
        } catch (FeignException f) {
            log.error(String.format("El usuario no existe en el sistema ", authentication.getName()));
        }
    }
}
