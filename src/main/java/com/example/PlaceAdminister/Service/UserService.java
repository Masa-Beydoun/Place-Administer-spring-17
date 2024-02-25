package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.DTO.UserDTO;
import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import com.example.PlaceAdminister.Repository.UserRepository;
import com.example.PlaceAdminister.Request.LogoutRequest;
import com.example.PlaceAdminister.Request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static java.time.LocalTime.now;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder encoder;
    private String userFilePath = "src/main/resources/Users.json";


    public List<UserEntity> getAllUsers(){
        return userRepository.readFromJsonFile(userFilePath);
    }

    public UserEntity register(UserEntity userEntity){
        return userRepository.writeToJsonFile(userEntity ,this.userFilePath);
    }
    //edit for token
    public UserEntity login(UserEntity userEntity){
        List<UserEntity> userEntities= userRepository.readFromJsonFile(userFilePath);
        for(int i=0;i<userEntities.size();i++){
            UserEntity user=userEntities.get(i);
            if(user.getUsername()==userEntity.getUsername() ) {
                if (user.getPassword() == userEntity.getPassword()) {
                    //generate token
                    return user;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public UserEntity logout(LogoutRequest request){
        List<UserEntity> userEntities= userRepository.readFromJsonFile(userFilePath);
        for(int i=0;i<userEntities.size();i++){
            UserEntity user=userEntities.get(i);
            if(user.getUsername()==request.getRequest().getUserName()) {
                if (user.getToken() == request.getToken()) {
                    user.setToken(null);
                    userRepository.UpdateById(user.getId(),user,userFilePath);
                    return user;
                } else {
                    return null;
                }
            }
        }
        return null;

    }
//
//    public UserDTO show(Long id)
//    {
//        return userRepository.searchDataById(id , this.userFilePath);
//    }
//
//
//
//    public UserDTO update(Long id , UserDTO userDTO){
//        return userRepository.UpdateById(id ,userDTO,this.userFilePath);
//    }
//
//    public void delete(Long id){
//        userRepository.deleteById(id,this.userFilePath);
//    }
//
////    public UserDTO login(UserRequest userRequest){
////        List<UserDTO> users=userRepository.readFromJsonFile(this.userFilePath);
////        for(int i=0;i<users.size();i++){
////            if(users.get(i).getPhoneNumber()== user.getPhoneNumber()){
////                if(users.get(i).getPassword()== user.getPassword())
////                    return users.get(i);
////                else new UserDTO("Wrong Passsword");
////            }
////        }
////        return new UserDTO("Not Found");
////    }
//
//    public UserDTO register(@RequestBody  UserRequest userRequest){
//        List<UserDTO> users=userRepository.readFromJsonFile(this.userFilePath);
//        for(int i=0;i<users.size();i++){
//            if(users.get(i).getPhoneNumber()== userRequest.getPhoneNumber()){
//                    return new UserDTO("already exist");
//            }
//        }
//        UserDTO user=store(new UserDTO(userRequest));
//        return user;
//    }

}

