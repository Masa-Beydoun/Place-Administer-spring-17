package com.example.PlaceAdminister.Security;

import com.example.PlaceAdminister.DTO.UserDTO;
import com.example.PlaceAdminister.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JwtController {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository repository;
//    UserService userService;

    @PostMapping("/login")
    public String auth(@RequestBody UserDTO user) {
        repository.UpdateById(user.getId(),user,"src/main/resources/Users.json");
        return jwtService.generateToken(user);
    }

    @GetMapping("/hello")
    public String message(UsernamePasswordAuthenticationToken auth) {
        UserDTO user = repository.findByUsername(auth.getName(),"src/main/resources/Users.json");
        return "Hello " + user.getUserName() + "\n";
    }
}
