package com.example.libraryapi.config;

import com.example.libraryapi.security.LoginSocialSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true) // Habilita as anotações @Secured e @RolesAllowed
public class SecurityConfiguration {
    @Bean
//    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginSocialSuccessHandler successHandler) throws Exception {
        return http
                .securityMatcher("/**")
                .csrf(AbstractHttpConfigurer::disable) // Habilitar quando for usar aplicação web (frontend)
                .httpBasic(Customizer.withDefaults()) // Habilita autenticação básica, um form prompt
                .formLogin(configurer ->
                        configurer.loginPage("/login").permitAll()
                )
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login/**").permitAll();
                    authorize.requestMatchers(HttpMethod.POST,"/usuarios/**").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> oauth2.loginPage("/login").successHandler(successHandler))
                .oauth2ResourceServer(oauth2RS-> oauth2RS.jwt(Customizer.withDefaults()))
                .build();
    }

    // Configuração para remover o prefixo "ROLE_" dos papéis
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        // Remove o prefixo "ROLE_" dos papéis
        return new GrantedAuthorityDefaults(""); // Deixa vazio para não adicionar o prefixo
    }

    // Configuração do conversor de autoridades do JWT
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var authConverter = new JwtGrantedAuthoritiesConverter();
        authConverter.setAuthorityPrefix("");

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authConverter);

        return converter;
    }
}
