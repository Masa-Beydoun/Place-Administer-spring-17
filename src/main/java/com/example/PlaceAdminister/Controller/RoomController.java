package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.Request.RoomRequest;
import com.example.PlaceAdminister.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService=new RoomService();


   @GetMapping("{place_id}/AllRooms")
    public ResponseEntity index(@PathVariable("place_id") Long place_id){
        if(place_id == null || place_id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
       List<RoomDTO> roomsList = roomService.getAllRooms(place_id);
       if(roomsList.isEmpty()){
           return ResponseEntity.status(200).body("there is no Rooms yet");
       }
        return ResponseEntity.ok(roomsList);
    }
    //checked

    @PostMapping("/{place_id}/newRoom")
    public ResponseEntity create(@RequestBody RoomRequest request , @PathVariable("place_id") Long place_id)
    {
        request.setPlaceId(place_id);
        if(request.getPlaceId() == null || request.getMax_num_of_chairs() == null || request.getCategory_id() == null){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        RoomDTO roomDTO = new RoomDTO(request);

//        System.out.println(request.getMax_num_of_chairs());
        RoomDTO room = roomService.store(roomDTO);
        if(room == null){
                return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
        }
        return ResponseEntity.ok(room);
    }
    @GetMapping("/{place_id}/show/{id}")
    public ResponseEntity show(@PathVariable("id") Long id ,@PathVariable("place_id") Long place_id){
        if(id == null || id<=0){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("d");
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        RoomDTO room = roomService.getItem(id);
        if(room == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        if(room.getPlaceId() != place_id){
            return ResponseEntity.status(403).body("you don't have permission to enter to this data");
        }
        else {
            return  ResponseEntity.ok(room);
        }
         }


    @PutMapping("/{place_id}/update/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody RoomRequest request ,@PathVariable("place_id") Long place_id){
        if(id == null || id<=0){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("d");
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(request.getPlaceId() == null || request.getMax_num_of_chairs() == null || request.getCategory_id() == null){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        RoomDTO roomDTO = new RoomDTO(request);
        RoomDTO room = roomService.getItem(id);
        if(room == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        if(room.getPlaceId() != place_id){
            return ResponseEntity.status(401).body("you don't have permission to enter to this data");
        }

        return ResponseEntity.ok(roomService.update(id ,roomDTO));
    }

    @DeleteMapping("/{place_id}/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id ,@PathVariable("place_id") Long place_id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        RoomDTO room= roomService.getItem(id);
        if(room == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        if(room.getPlaceId() != place_id){
            return ResponseEntity.status(401).body("you don't have permission to enter to this data");
        }

    @GetMapping("findByPlaceId/{id}")
    public List<RoomDTO> showByPlaceId(@PathVariable("id") Long id){
        return roomService.showRoomsByPlaceId(id);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id){
        roomService.delete(id);
        return ResponseEntity.ok("Delete Done successfully");
    }
}

