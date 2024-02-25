package com.example.PlaceAdminister.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import static com.example.PlaceAdminister.Security.ApplicationUserRole.*;

import static com.example.PlaceAdminister.Security.Role.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/users/newOnePlaceAdmin").hasRole(SUPER_ADMIN.name())
                        .requestMatchers("/api/v1/users/newAdmin").hasRole(ONE_PLACE_ADMIN.name())
                        .requestMatchers("/api/v1/rooms/**").hasRole(ADMIN.name())
                        .requestMatchers("/api/v1/tables/**").hasRole(ADMIN.name())
                        .requestMatchers("/api/v1/roomCategories/**").hasRole(ADMIN.name())
                        .requestMatchers("/api/v1/table-category/**").hasRole(ADMIN.name())
                        .requestMatchers("/api/v1/read/**").hasRole(USER.name())
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
                .build();
    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        List<UserDetails> userDetails= new ArrayList<>();
//        List<UserEntity> userEntities=userRepository.readFromJsonFile("src/main/resources/Users.json");
//
//        for(int i=0;i<userEntities.size();i++){
//            userDetails.add(User.withDefaultPasswordEncoder()
//                            .username(userEntities.get(i).getUsername())
////                .password(passwordEnoder.encode("password"))
//                            .password(userEntities.get(i).getPassword())
//                            .roles(userEntities.get(i).getRole().name())
//                            .build()
//            );
//            System.out.println(userDetails.get(i).toString());
//
//        }
//
//        return new InMemoryUserDetailsManager(
//                userDetails
//        );
//    }


}
