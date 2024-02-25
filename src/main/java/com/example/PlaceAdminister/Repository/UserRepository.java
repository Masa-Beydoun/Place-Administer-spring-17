package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.DTO.UserDTO;
import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository{
    public List<UserEntity> readFromJsonFile(String filePath) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<UserEntity> models = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
            for(int i=0;i<models.size();i++){
                System.out.println(models.get(i).toString());
            }
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }

    }

    public UserEntity writeToJsonFile(UserEntity models, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<UserEntity> userEntities= readFromJsonFile(filePath);
            Long id= Long.valueOf(1);
            if(!(userEntities.size()==0)) id=(Long)userEntities.get(userEntities.size()-1).getId()+1;
            models.setId(id);
            userEntities.add(models);

            objectMapper.writeValue(new File(filePath), userEntities);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return models;
    }


    public UserEntity searchDataById(Long id , String filePath) {
        List<UserEntity> dataList = readFromJsonFile(filePath);
        return dataList.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(Long id , String filePath){
        try {
            // Step 1: Read the JSON file and parse it
            File jsonFile = new File(filePath);
            FileInputStream fis = new FileInputStream(jsonFile);
            JSONTokener tokener = new JSONTokener(fis);
            JSONArray jsonArray = new JSONArray(tokener);
            fis.close(); // Close the FileInputStream

            // Step 2: Identify and remove the specific element
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject element = jsonArray.getJSONObject(i);
                if (element.getLong("id") == id) {
                    jsonArray.remove(i); // Remove the JSONObject at index i
                    break; // Exit the loop once the element is removed
                }
            }

            // Step 3: Write the updated data back to the JSON file
            FileWriter fileWriter = new FileWriter(jsonFile);
            jsonArray.write(fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }



    public UserEntity findByUsername(String userName,String filePath){
        List<UserEntity> users= readFromJsonFile(filePath);
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

    public UserEntity UpdateById(Long id , UserEntity userEntity , String filePath){
        try {
            // Step 1: Read the JSON file and parse it
            File jsonFile = new File(filePath);
            FileInputStream fis = new FileInputStream(jsonFile);
            JSONTokener tokener = new JSONTokener(fis);
            JSONArray jsonArray = new JSONArray(tokener);

            // Step 2 and 3: Identify and update the specific element
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject element = jsonArray.getJSONObject(i);
                if (element.getLong("id") == (id)) { // Assuming "id" is the identifier for the element
//                    System.out.println(element.getInt("id"));
                    element.put("id" , id);
                    element.put("phoneNumber", userEntity.getPhoneNumber());
                    element.put("password", userEntity.getPassword());
                    element.put("role", userEntity.getRole());
                    element.put("token", userEntity.getToken());
                    element.put("username", userEntity.getUsername());
                    element.put("authorities", userEntity.getAuthorities());
                    element.put("isAccountNonExpired", userEntity.isAccountNonExpired());
                    element.put("isAccountNonLocked", userEntity.isAccountNonLocked());
                    element.put("isCredentialsNonExpired", userEntity.isCredentialsNonExpired());
                    element.put("isEnabled", userEntity.isEnabled());



                }
            }

            // Step 4: Write the updated data back to the JSON file
            FileWriter fileWriter = new FileWriter(jsonFile);
            jsonArray.write(fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return userEntity;
    }


}
