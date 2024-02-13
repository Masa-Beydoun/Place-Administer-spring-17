package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private  static final List<UserEntity> USER_ENTITIES= Arrays.asList(
            new UserEntity(1L,"masa","12345678","USER", 947439509),
            new UserEntity(2L,"wassem","12345678","ADMIN", 947439509),
            new UserEntity(3L,"kareem","12345678","SUPER_ADMIN", 947439509)
    );
    @GetMapping("/{userId}")
    public UserEntity getUser(@PathVariable("userId") Long userId){
        return USER_ENTITIES.stream()
                .filter(userEntity -> userId.equals(userEntity.getId()))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException(
                        "user" + userId + "does not exist"
        ));
    }
}
