/*
package com.ensa.OrderMngmnt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Override this method to configure the AuthenticationManagerBuilder.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // In-memory authentication example (replace with your custom authentication provider if needed)
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    // Override this method to configure the HttpSecurity.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/orders/**").hasAnyRole("USER", "ADMIN") // Secure API endpoints
                .antMatchers("/api/orders/history/**").hasAnyRole("USER", "ADMIN") // Secure API endpoints
                .anyRequest().authenticated() // All other requests need to be authenticated
                .and()
                .httpBasic() // Use HTTP Basic authentication
                .and()
                .csrf().disable(); // Disable CSRF (Cross-Site Request Forgery) protection
    }

    // Bean for PasswordEncoder (used to encrypt passwords)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
*/
