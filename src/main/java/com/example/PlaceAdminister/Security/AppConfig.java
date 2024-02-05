package com.example.PlaceAdminister.Security;


import com.example.PlaceAdminister.DTO.UserDTO;
import com.example.PlaceAdminister.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//form abd al aziz
@Configuration
public class AppConfig {
    @Autowired
    UserRepository repository;
    String userFilePath="src/main/resources/Places.json";

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) repository.findByUsername(username,userFilePath);

    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
