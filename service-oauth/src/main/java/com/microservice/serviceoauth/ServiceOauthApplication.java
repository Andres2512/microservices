package com.microservice.serviceoauth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ServiceOauthApplication implements CommandLineRunner {

    private final BCryptPasswordEncoder passwordEncoder;

    public ServiceOauthApplication(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceOauthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String password = "12345";
        for (int i= 0; i<4 ; i++){
            String passwordByCrypt = passwordEncoder.encode(password);
            System.out.println(passwordByCrypt);
        }
    }
}
