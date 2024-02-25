package com.example.PlaceAdminister.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String role;
    private int phoneNumber;
    private String password;

}
