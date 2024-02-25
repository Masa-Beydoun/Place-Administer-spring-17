package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomService {
    @Autowired
    private RoomEntity rooms;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomCategoryService roomCategoryService;

    @Autowired
    private PlaceService placeService;
    private String roomFilepath = "src/main/resources/Rooms.json";


    public List<RoomDTO> getAllRooms(Long id) {
         List<RoomDTO> roomList = roomRepository.readFromJsonFile(roomFilepath);
         List<RoomDTO> myRoomList = roomList.stream().filter(i->i.getPlaceId().equals(id)).toList();
        return myRoomList;

    }

    public RoomDTO store(RoomDTO roomDTO) {
        if(roomCategoryService.getAllRoomCategories(roomDTO.getPlaceId()).size() == 0){
            return new RoomDTO("you should add some room category first");
        }
        if(placeService.getAllPlaces().stream().filter(i->i.getId().equals(roomDTO.getPlaceId())).toList().size() == 0){
            return new RoomDTO("there is no place such this ,add place first");
        }
        return roomRepository.writeToJsonFile(roomDTO ,this.roomFilepath);
    }


    public RoomDTO getItem(Long id)
    {
      RoomDTO room =   roomRepository.searchDataById(id , this.roomFilepath);
        return room;
    }


    public RoomDTO update(Long id , RoomDTO roomDTO){
        return roomRepository.UpdateById(id ,roomDTO,this.roomFilepath);
    }

    public List<RoomDTO> showRoomsByPlaceId(Long id)
    {
        return  roomRepository.searchByPlaceId(id , this.roomFilepath);
    }


    public void delete(Long id){
        roomRepository.deleteById(id,this.roomFilepath);
    }
}

