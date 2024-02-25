package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.Repository.UserRepository;
import com.example.PlaceAdminister.Request.RegisterRequest;
import com.example.PlaceAdminister.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    //super admin       to add new places and their admin
    //one place admin   to add admins in that place

    @PostMapping("/newOnePlaceAdmin")
    public ResponseEntity addNewOnePlaceAdmin(@RequestBody RegisterRequest request){
        return userService.addOnePlaceAdmin(request);
    }

    @PostMapping("/newAdmin")
    public ResponseEntity addNewAdmin(@RequestBody RegisterRequest request){
        return  userService.addAdmin(request);
    }

//    @PostMapping("/newPlaceAdmin")
//    public ResponseEntity addNewPlaceAdmin(@RequestBody RegisterRequest request){
//        return userService.addOnePlaceAdmin(request);
//    }
}
