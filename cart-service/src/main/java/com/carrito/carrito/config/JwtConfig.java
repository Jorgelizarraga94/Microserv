package com.carrito.carrito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

@Configuration
public class JwtConfig {
    @Bean
    public JwtDecoder jwtDecoder() {
        // Esto le dice explícitamente a Spring dónde obtener las llaves de Auth0
        return JwtDecoders.fromIssuerLocation("https://logintienda.us.auth0.com/");
    }
}
