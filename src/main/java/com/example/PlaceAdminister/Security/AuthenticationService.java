package com.example.PlaceAdminister.Security;

import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import com.example.PlaceAdminister.Repository.UserRepository;
import com.example.PlaceAdminister.Request.AuthenticationRequest;
import com.example.PlaceAdminister.Request.RegisterRequest;
import com.example.PlaceAdminister.Response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private String file="src/main/resources/Users.json";

    public String register(RegisterRequest request,Role role) throws Exception {
        UserEntity user1 = repository.findByPhoneNumber(request.getPhoneNumber(), file);
        if(user1!=null) throw new Exception();
        UserEntity user=new UserEntity(request,Role.USER);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(role);
        System.out.println(user);
        repository.store(user);
        var jwtToken = jwtService.generateToken(user);
        return jwtToken;
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();

    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );
        UserEntity user=repository.findByPhoneNumber(request.getPhoneNumber(),file);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
