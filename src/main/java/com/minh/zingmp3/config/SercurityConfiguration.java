package com.minh.zingmp3.config;


import com.cloudinary.provisioning.Account;
import com.minh.zingmp3.model.Role;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SercurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity.csrf(csrf->csrf.disable());
//         httpSecurity.authorizeHttpRequests(
//                        auth-> auth.requestMatchers("/","/api/auth/register","/api/auth/authentication").permitAll().requestMatchers(HttpMethod.POST, "/api/v1/albums","/api/v1/artists","/api/v1/songs","api/v1/categories").hasAnyAuthority(Role.ADMIN.name())
//                                .anyRequest().permitAll()
//                ).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                ;
        httpSecurity.authorizeHttpRequests(auth->auth.anyRequest().permitAll());
         return httpSecurity.build();
    }
}
