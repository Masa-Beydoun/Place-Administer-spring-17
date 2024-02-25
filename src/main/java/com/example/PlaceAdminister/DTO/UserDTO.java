package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Repository.TableCategoryRepository;
import com.example.PlaceAdminister.Request.TableRequest;
import com.example.PlaceAdminister.Request.UserRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserDTO extends AbstractDTO{
    private Long id;
    private String userName;
    private String role;
    private String phoneNumber;
    String message;
    private String password;

    public UserDTO(UserRequest userRequest) {
        this.userName=userRequest.getUserName();
        this.role=userRequest.getRole().name();
        this.phoneNumber=userRequest.getPhoneNumber();
        this.password=userRequest.getPassword();
    }

    public UserDTO(String message){
        this.message = message;
    }


}
