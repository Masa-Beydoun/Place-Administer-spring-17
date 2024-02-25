package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class UserRepository{
    private final String userFilePath="src/main/resources/Users.json";
    public List<UserEntity> readFromJsonFile(String filePath) {
        List<UserEntity> models=null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
             models = objectMapper.readValue(new File(userFilePath), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public UserEntity writeToJsonFile(UserEntity newUser, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<UserEntity> userEntities= readFromJsonFile(filePath);
            System.out.println(userEntities.toString());
            Long id= 1L;
            if(!(userEntities.size()==0)) id=userEntities.get(userEntities.size()-1).getId()+1;
            newUser.setId(id);
            userEntities.add(newUser);
            System.out.println(userEntities);

            objectMapper.writeValue(new File(filePath), userEntities);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return newUser;
    }
    public UserEntity searchDataById(Long id , String filePath) {
        List<UserEntity> dataList = readFromJsonFile(filePath);
        for(int i=0;i<dataList.size();i++){
            if(dataList.get(i).getId()==id)
                return dataList.get(i);
        }
        return null;
    }
    public void delete(UserEntity userEntity, String filePath){
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
                if (element.getLong("id") == userEntity.getId()) {
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
        List<UserEntity> dataList = readFromJsonFile(filePath);
        for(int i=0;i<dataList.size();i++){
            if(dataList.get(i).getUsername().equals(userName))
                return dataList.get(i);
        }
        return null;
    }
    public UserEntity findByPhoneNumber(String number, String filePath){
        List<UserEntity> dataList = readFromJsonFile(filePath);
        for(int i=0;i<dataList.size();i++){
            if(dataList.get(i).getPhoneNumber().equals(number))
                return dataList.get(i);
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
                    element.put("username", userEntity.getUsername());
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

    public UserEntity store(UserEntity userEntity){
        return writeToJsonFile(userEntity,userFilePath);
    }

    public UserEntity index(int id){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<UserEntity> models = objectMapper.readValue(new File(userFilePath), new TypeReference<>() {});
            for(int i=0;i<models.size();i++){
                if(models.get(i).getId()==id) return models.get(i);
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }


}
