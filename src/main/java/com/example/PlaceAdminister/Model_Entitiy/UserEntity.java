package com.example.PlaceAdminister.Model_Entitiy;
import com.example.PlaceAdminister.Request.RegisterRequest;
import com.example.PlaceAdminister.Security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Collection;
import java.util.List;

import static com.example.PlaceAdminister.Security.Role.USER;
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonIgnore
    private boolean enabled;
    @JsonIgnore
    private boolean isAccountNonExpired;
    @JsonIgnore
    private boolean isAccountNonLocked;
    @JsonIgnore
    private boolean isCredentialsNonExpired;
    @JsonIgnore
    private  Collection<? extends GrantedAuthority> authorities;



    public UserEntity(RegisterRequest request,Role role){
        this.role=role;
        phoneNumber = request.getPhoneNumber();
        password = request.getPassword();
        username = request.getUsername();
    }
//    public UserEntity(UserHelperEntity user){
//        this.password=user.getPassword();
//        this.id= user.getId();
//        this.phoneNumber=user.getPhoneNumber();
//        this.username=user.getUsername();
//        this.role=user.getRole();
//    }
//    @JsonSerialize(using = GrantedAuthoritySerializer.class)
//    @JsonDeserialize(using = GrantedAuthorityDeserializer.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
