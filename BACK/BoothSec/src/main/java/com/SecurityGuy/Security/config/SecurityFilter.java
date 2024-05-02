package com.SecurityGuy.Security.config;

import com.SecurityGuy.Security.enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    // We can inject in
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Inject the HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Call the http object
        // Many configurations
        // Not using session but JWT, old method uses session instead of JWT.
        // Disable csrf (prevent session stealing).
        // Use Lambda pass stateless session creation policy to management
        // Declare which endpoints are private or public and which roles can use
        // auth/authenticate as login is public, same for register new user
        // Whenever there is an error, everything is routed
        // Deny all endpoints that are not specified here.
        http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( authConfig -> {
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/register").permitAll();
                    authConfig.requestMatchers("/error").permitAll();

                    authConfig.requestMatchers(HttpMethod.POST, "/user/seller").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/user/buyer").hasAuthority(Permission.EDIT_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/user/self").hasAuthority(Permission.READ_PRODUCTS.name());
                    authConfig.requestMatchers(HttpMethod.PATCH, "/user/self").hasAuthority(Permission.READ_PRODUCTS.name());

                    authConfig.requestMatchers(HttpMethod.GET, "/products").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/products").permitAll();
                    authConfig.requestMatchers(HttpMethod.PUT, "/products").hasAuthority(Permission.EDIT_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.PATCH, "/products").hasAuthority(Permission.EDIT_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/products").hasAuthority(Permission.EDIT_PRODUCT.name());


                    authConfig.requestMatchers(HttpMethod.PUT, "/purchase").hasRole("BUYER");
                    authConfig.requestMatchers(HttpMethod.GET, "/order/history").hasRole("BUYER");
                    authConfig.requestMatchers(HttpMethod.GET, "/order/salehistory").hasRole("SELLER");
                    authConfig.requestMatchers(HttpMethod.PATCH, "/order/buyerpatch").hasRole("BUYER");
                    authConfig.requestMatchers(HttpMethod.PATCH, "/order/sellerpatch").hasRole("SELLER");

                    authConfig.anyRequest().denyAll();


                });
        // I decided to protect my endpoints this way but I think may be more logical ways?
        // Build the http object
        return http.build();

    }
}
