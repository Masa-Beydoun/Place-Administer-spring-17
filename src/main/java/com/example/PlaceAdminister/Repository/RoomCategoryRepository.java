package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomCategoryRepository {

    public List<RoomCategoryDTO> readFromJsonFile(String filePath) {
        String filepath1 = "src/main/resources/RoomCategories.json";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<RoomCategoryDTO> models = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public RoomCategoryDTO writeToJsonFile(RoomCategoryDTO models, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<RoomCategoryDTO> roomCategory= readFromJsonFile(filePath);
            Long id=(Long)roomCategory.get(roomCategory.size()+1).getId();
            models.setId(id);
            roomCategory.add(models);

            objectMapper.writeValue(new File(filePath), roomCategory);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return models;
    }


    public RoomCategoryDTO searchDataById(Long id , String filePath) {
        List<RoomCategoryDTO> dataList = readFromJsonFile(filePath);
        return dataList.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public RoomCategoryDTO UpdateById(Long id , RoomCategoryDTO tableCategoryDTO , String filePath){
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
                    System.out.println(element.getInt("id"));
                    element.put("id" , id);
//                    element.put("shape", tableCategoryDTO.getShape());
//                    element.put("num_of_seats", tableCategoryDTO.getNum_of_seats());
                    // Add more modifications as needed
                }
            }

            FileWriter fileWriter = new FileWriter(jsonFile);
            jsonArray.write(fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return tableCategoryDTO;
    }


}
