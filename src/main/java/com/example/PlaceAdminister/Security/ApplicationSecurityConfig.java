package com.example.PlaceAdminister.Security;

import com.example.PlaceAdminister.DTO.UserDTO;
import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import com.example.PlaceAdminister.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//import static com.example.PlaceAdminister.Security.ApplicationUserRole.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.PlaceAdminister.Security.ApplicationUserRole.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

//    private final PasswordEncoder passwordEnoder;
//    @Autowired
//    public ApplicationSecurityConfig(PasswordEncoder passwordEnoder) {
//        this.passwordEnoder = passwordEnoder;
//    }

    @Autowired
    private UserRepository userRepository;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("api/v1/places/allPlaces").hasRole(SUPER_ADMIN.name())
                        .requestMatchers("api/v1/rooms/allRooms").hasRole(ADMIN.name())
                        .requestMatchers("api/v1/places/allPlaces").permitAll()
                        .requestMatchers("api/v1/rooms/findByPlaceId").hasRole(SUPER_ADMIN.name())
//                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
//    }
//
//
//
//    @Bean
//    public EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean() {
//        EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean =
//                EmbeddedLdapServerContextSourceFactoryBean.fromEmbeddedLdapServer();
//        contextSourceFactoryBean.setPort(0);
//        return contextSourceFactoryBean;
//    }
//
//    @Bean
//    AuthenticationManager ldapAuthenticationManager(
//            BaseLdapPathContextSource contextSource) {
//        LdapBindAuthenticationManagerFactory factory =
//                new LdapBindAuthenticationManagerFactory(contextSource);
//        factory.setUserDnPatterns("uid={0},ou=people");
//        factory.setUserDetailsContextMapper(new PersonContextMapper());
//        return factory.createAuthenticationManager();
//    }
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }
//
//        @Bean
//        public UserDetailsManager users(DataSource dataSource) {
//            UserDetails user = User.withDefaultPasswordEncoder()
//                    .username("user")
//                    .password("password")
//                    .roles("USER")
//                    .build();
//            JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//            users.createUser(user);
//            return users;
//        }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        List<UserDetails> userDetails= new ArrayList<>();
        List<UserEntity> userDTOS=userRepository.readFromJsonFile("src/main/resources/Users.json");

        for(int i=0;i<userDTOS.size();i++){
            userDetails.add(User.withDefaultPasswordEncoder()
                            .username(userDTOS.get(i).getUsername())
//                .password(passwordEnoder.encode("password"))
                            .password(userDTOS.get(i).getPassword())
                            .roles(userDTOS.get(i).getRole())
                            .build()
            );
            System.out.println(userDetails.get(i).toString());

        }

        return new InMemoryUserDetailsManager(
                userDetails
        );
    }


}
