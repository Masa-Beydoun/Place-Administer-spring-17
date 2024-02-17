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
    private String roomFilepath = "src/main/resources/Rooms.json";


    public List<RoomDTO> getAllRooms(Long id) {
         List<RoomDTO> roomList = roomRepository.readFromJsonFile(roomFilepath);
         List<RoomDTO> myRoomList = roomList.stream().filter(i->i.getPlaceId().equals(id)).toList();
        return myRoomList;

    }

    public RoomDTO store(RoomDTO roomDTO) {
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



//    public RoomDTO reserveRoom(Long id , Date date){
//        RoomDTO roomDTO = roomRepository.searchDataById(id ,filepath);
//        if(roomDTO != null){
//            if(roomDTO.getStatus()!=1) {
//                roomDTO.setStatus(2);
//                roomDTO.setTime_of_reservation(date);
//                roomRepository.UpdateById(id, roomDTO, filepath);
//                return roomDTO;
//            }
//            else return null;
//        }
//        else {
//            return null;
//        }
//    }

//    public RoomDTO cancelRoomReservation(Long id ) {
//        RoomDTO roomDTO = roomRepository.searchDataById(id, filepath);
//        if (roomDTO != null) {
////            if(tableDTO.getStatus() == 2){
//            roomDTO.setStatus(1);
//            roomDTO.setTime_of_reservation(null);
//
//            roomRepository.UpdateById(id, roomDTO, filepath);
//            return roomDTO;
//        } else {
//            return null;
//        }
//    }
    public void delete(Long id){
        roomRepository.deleteById(id,this.roomFilepath);
    }
}

