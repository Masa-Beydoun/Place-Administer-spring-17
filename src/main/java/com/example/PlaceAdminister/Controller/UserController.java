package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.DTO.UserDTO;
import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import com.example.PlaceAdminister.Repository.UserRepository;
import com.example.PlaceAdminister.Request.UserRequest;
import com.example.PlaceAdminister.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @GetMapping("/allUsers")
    public List<UserEntity> getUser(){
        return  userService.getAllUsers();
    }
    @PostMapping("/register")
    public UserEntity register(@RequestBody UserRequest userRequest){
        UserEntity user = new UserEntity(userRequest);
        return userService.register(user);
    }

    @PutMapping("login")
    public UserEntity login(@RequestBody UserRequest userRequest){
        UserEntity user = new UserEntity(userRequest);
        return userService.login(user);
    }

    @PutMapping("logout")
    public UserEntity logout(@RequestBody LogoutRequest logoutRequest){
        return userService.login(logoutRequest);
    }


}
