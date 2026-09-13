package com.codingsrv.RoleBasedAuthorization.config;

import com.codingsrv.RoleBasedAuthorization.entities.util.Role;
import com.codingsrv.RoleBasedAuthorization.filter.JwtAuthFilter;
import com.codingsrv.RoleBasedAuthorization.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private static final String[] publicRoutes = {"/error", "/auth/**" ,"/home.html"};  // public routes

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())// disable the csrf and make session stateless
                .sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicRoutes).permitAll()
                        .requestMatchers(HttpMethod.GET,"/posts/**").permitAll()  // any one can access GET method of the 'post' controller
                        .requestMatchers(HttpMethod.POST,"/posts/**")
                             .hasAnyRole(Role.ADMIN.name(), Role.CREATOR.name())  // only Authorized user can access POST method of the 'post' controller
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2config->oauth2config
                        .failureUrl("/login?error=true")
                        .successHandler(oAuth2SuccessHandler)
                )
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }


}
