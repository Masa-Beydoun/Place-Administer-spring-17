package com.example.PlaceAdminister.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.PlaceAdminister.DTO.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    @Autowired
    private PasswordEncoder encoder;
    public List<UserEntity> readFromJsonFile(String filePath) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<UserEntity> models = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public UserEntity writeToJsonFile(UserEntity models, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<UserEntity> tables= readFromJsonFile(filePath);
            int id= 1;
            if(!(tables.size()==0)) id=tables.get(tables.size()-1).getId()+1;
            models.setId(id);
            tables.add(models);

            objectMapper.writeValue(new File(filePath), tables);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return models;
    }


    public UserEntity save(UserEntity user){
    UserEntity newUser=user;
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        return writeToJsonFile(newUser ,"src/main/resources/Users.json");
  }

    public UserEntity findByEmail(String email){
      String filePath="src/main/resources/Users.json";
      List<UserEntity> users= readFromJsonFile(filePath);
      // Step 2 and 3: Identify and update the specific element
      for (int i = 0; i < users.size(); i++) {
          if (users.get(i).getEmail()== email) { // Assuming "id" is the identifier for the element
              return users.get(i);
          }
      }
      return null;
  }
    public UserEntity findByUsername(String userName,String filePath){
        filePath="src/main/resources/Users.json";
        List<UserEntity> users= readFromJsonFile(filePath);
        // Step 2 and 3: Identify and update the specific element
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername() == (userName)) { // Assuming "id" is the identifier for the element
                return users.get(i);
            }
        }
        return null;
    }

    public UserEntity findByPhoneNumber(Long number, String filePath){
        List<UserEntity> users= readFromJsonFile(filePath);
        // Step 2 and 3: Identify and update the specific element
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPhoneNumber() == (number)) { // Assuming "id" is the identifier for the element
                return users.get(i);
            }
        }
        return null;
    }

}
