package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.Model_Entitiy.RoomCategoryEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Request.RoomRequest;
import com.example.PlaceAdminister.Service.RoomService;
import org.apache.coyote.Response;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService=new RoomService();

    @GetMapping("/allRooms")
    public List<RoomDTO> index(){
        return roomService.getAllRooms();
    }
    //checked

    @PostMapping("/newRoom")
    public RoomDTO create(@RequestBody RoomRequest request)
    {
        RoomDTO roomDTO = new RoomDTO(request);
        return roomService.store(roomDTO);
    }
    //checked

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id){
        return roomService.show(id);
    }
    //checked

    @PutMapping("update/{id}")
    public RoomDTO edit(@PathVariable("id") Long id ,@RequestBody RoomRequest request){
        RoomDTO roomDTO = new RoomDTO(request);
        if(roomDTO!=null)
            return roomService.update(id ,roomDTO);
        else return null;
    }
    //checked
//    @PostMapping("/reservationRoom/{id}")
//    public RoomDTO reserveRoom(@PathVariable("id") Long id ,@RequestBody RoomRequest request){
//        return roomService.reserveRoom(id,request.getTime_of_reservation());
//    }
//    @PostMapping("/cancelreserve/{id}")
//    public RoomDTO cancelReserve(@PathVariable("id") Long id){
//        return roomService.cancelRoomReservation(id);
//    }


    @GetMapping("findByPlaceId/{id}")
    public List<RoomDTO> showByPlaceId(@PathVariable("id") Long id){
        return roomService.showRoomsByPlaceId(id);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id){
        roomService.delete(id);
    }
}

