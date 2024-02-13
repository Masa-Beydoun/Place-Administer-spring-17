package com.example.PlaceAdminister.Security;

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
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

//    private final PasswordEncoder passwordEnoder;
//    @Autowired
//    public ApplicationSecurityConfig(PasswordEncoder passwordEnoder) {
//        this.passwordEnoder = passwordEnoder;
//    }

    //amigo
//    @Bean
//    public int  configure(HttpSecurity http) throws Exception{
//        int x=1;
//        http
//                .authorizeRequests()
////                .requestMatchers("/").permitAll()
////                .requestMatchers("api/v1/places/newPlace").hasRole(SUPER_ADMIN.name())
////                .requestMatchers("api/v1/rooms/findByPlaceId/{id}").hasRole(USER.name())
////                .requestMatchers("api/v1/rooms/findByPlaceId/{id}").hasRole(ADMIN.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//        return 1;
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("api/v1/places/newPlace").hasRole(SUPER_ADMIN.name())
//                        .requestMatchers("api/v1/rooms/findByPlaceId/{id}").permitAll()
//                        .requestMatchers("api/v1/rooms/findByPlaceId/{id}").hasRole(ADMIN.name())
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
        UserDetails masa = User.withDefaultPasswordEncoder()
                .username("masa")
//                .password(passwordEnoder.encode("password"))
                .password("password")
                .roles("student")
                .build();
        UserDetails wassem = User.builder()
                .username("wassem")
//                .password(passwordEnoder.encode("password"))
                .password("password")
//                .roles(SUPER_ADMIN.name())
                .build();
        UserDetails kareem = User.builder()
                .username("kareem")
//                .password(passwordEnoder.encode("password"))
                .password("password")
//                .roles(USER.name())
                .build();



        return new InMemoryUserDetailsManager(
                masa,
                wassem,
                kareem);
    }


}
