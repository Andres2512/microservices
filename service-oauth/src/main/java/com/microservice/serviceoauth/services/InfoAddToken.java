package com.microservice.serviceoauth.services;

import com.microservice.commonsuser.models.entity.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAddToken implements TokenEnhancer {


    private final IUserService userService;

    public InfoAddToken(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> info = new HashMap<>();
        User user =  userService.findByUserName(oAuth2Authentication.getName());
        info.put("name", user.getName());
        info.put("surname",user.getSurname());
        info.put("email",user.getEmail());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);

        return oAuth2AccessToken;

    }
}
