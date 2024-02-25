package com.example.PlaceAdminister.Request;

import com.example.PlaceAdminister.Security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    private Long id;
    private String UserName;
    private Role role;
    private String phoneNumber;
    private String password;

}
