package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.Model_Entitiy.RoomCategoryEntity;
import com.example.PlaceAdminister.Repository.RoomCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomCategoryService {
    @Autowired
    private RoomCategoryEntity roomCategories;
    @Autowired
    private RoomCategoryRepository roomCategoryRepository;
    private String filepath = "src/main/resources/RoomCategories.json";


    public List<RoomCategoryDTO> getAllRoomCategories(Long id) {
           List<RoomCategoryDTO> roomCategoryDTOList =    roomCategoryRepository.readFromJsonFile(filepath);
           List<RoomCategoryDTO> myRoomCategoryList = roomCategoryDTOList.stream().filter(i->i.getPlace_id().equals(id)).toList();
           return myRoomCategoryList;
    }

    public RoomCategoryDTO store(RoomCategoryDTO roomCategoryDTO) {
        return roomCategoryRepository.writeToJsonFile(roomCategoryDTO ,this.filepath);
    }

    public RoomCategoryDTO getRoomCategory(Long id)
    {
        return roomCategoryRepository.searchDataById(id , this.filepath);
    }


    public RoomCategoryDTO update(Long id , RoomCategoryDTO roomCategoryDTO){
        return roomCategoryRepository.UpdateById(id ,roomCategoryDTO,this.filepath);
    }

    public void delete(Long id){
        roomCategoryRepository.deleteById(id,filepath);
    }

}
