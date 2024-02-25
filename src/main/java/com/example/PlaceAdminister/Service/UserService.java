package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import com.example.PlaceAdminister.Repository.UserRepository;
import com.example.PlaceAdminister.Request.RegisterRequest;
import com.example.PlaceAdminister.Security.AuthenticationService;
import com.example.PlaceAdminister.Security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.time.LocalTime.now;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AuthenticationService service;
    //    @Autowired
//    private PasswordEncoder encoder;
    private String userFilePath = "src/main/resources/Users.json";


    public UserEntity loadByPhoneNumber(String phoneNumber){
        return userRepository.findByPhoneNumber(phoneNumber,userFilePath);
    }
//    public ResponseEntity addOnePlaceAdmin(RegisterRequest request){
////        UserEntity user = new UserEntity(request,Role.ADD_ONE_PLACE_ADMIN);
//        try {
//            return ResponseEntity.status(200).body(service.register(request , Role.SUPER_ADMIN));
//        } catch (Exception e) {
//            return ResponseEntity.status(401).body("user already found");
//        }
//    }
    public ResponseEntity addOnePlaceAdmin(RegisterRequest request){
        try {
            return ResponseEntity.status(200).body(service.register(request , Role.ONE_PLACE_ADMIN));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("user already found");
        }
    }

    public ResponseEntity addAdmin(RegisterRequest request){
        try {
            return ResponseEntity.status(200).body(service.register(request , Role.ADMIN));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("user already found");
        }
    }

}