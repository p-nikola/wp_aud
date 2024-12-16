package com.example.aud1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider authProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider authProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((requests) -> requests.requestMatchers("/", "/home", "/assets/**", "/register")
                        .permitAll().requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/change-role").hasRole("ADMIN").anyRequest().authenticated()).httpBasic(Customizer.withDefaults())
                .formLogin((form) -> form.loginPage("/login").permitAll().failureUrl("/login?error=BadCredentials").defaultSuccessUrl("/products", true))
                .logout((logout) -> logout.logoutUrl("/logout").clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/login"))
                .exceptionHandling((ex) -> ex.accessDeniedPage("/access_denied"));


        return http.build();

    }


    //In memory authentication
    //@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("nikola").password(passwordEncoder.encode("np")).roles("USER").build();
        UserDetails user2 = User.builder().username("kalin").password(passwordEncoder.encode("kp")).roles("USER").build();
        UserDetails admin = User.builder().username("admin").password(passwordEncoder.encode("ad")).roles("ADMIN").build();



        return new InMemoryUserDetailsManager(user1,user2,admin);
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);

        return authenticationManagerBuilder.build();
    }


}
