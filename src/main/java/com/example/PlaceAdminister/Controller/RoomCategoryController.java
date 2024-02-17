package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.Request.RoomCategoryRequest;
import com.example.PlaceAdminister.Service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@RestController
@RequestMapping("/api/v1/roomCategories")
public class RoomCategoryController {
    @Autowired
    private RoomCategoryService roomCategoryService;

    @GetMapping("{place_id}/allRoomCategories")
    public ResponseEntity getAllRoomCategories(@PathVariable("place_id") Long id) {
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        List<RoomCategoryDTO> roomCategoryList = roomCategoryService.getAllRoomCategories(id);
        if(roomCategoryList.isEmpty()){
            return ResponseEntity.status(200).body("there is no room category yet");
        }
        return ResponseEntity.ok(roomCategoryList);
    }

    @PostMapping("{place_id}/newRoomCategory")
    public ResponseEntity addRoomCategory(@RequestBody RoomCategoryRequest roomCategoryRequest ,@PathVariable("place_id") Long place_id)
    {
        roomCategoryRequest.setPlace_id(place_id);
        if(roomCategoryRequest.getType() == null || roomCategoryRequest.getPlace_id() ==null){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        RoomCategoryDTO roomCategoryDTO = new RoomCategoryDTO(roomCategoryRequest);
        RoomCategoryDTO newRoomCategory = roomCategoryService.store(roomCategoryDTO);
        if(newRoomCategory == null){
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
        }
        return ResponseEntity.ok(newRoomCategory);
    }

    @GetMapping("{place_id}/show/{id}")
    public ResponseEntity show(@PathVariable("id") Long id,@PathVariable("place_id") Long place_id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(place_id == null || place_id<=0){
            return ResponseEntity.badRequest().body("Invalid place_id");
        }
        RoomCategoryDTO roomCategory = roomCategoryService.getRoomCategory(id);
        if(roomCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        if(roomCategory.getPlace_id() !=place_id){
            return ResponseEntity.status(403).body("you don't have permission to enter to this data");
        }
        return ResponseEntity.ok(roomCategory);
    }

    @PutMapping("{place_id}/update/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody RoomCategoryRequest request , @PathVariable("place_id") Long place_id){
        if(id == null || id<=0)
        {
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(place_id == null || place_id<=0)
        {
            return ResponseEntity.badRequest().body("Invalid place_id");
        }
        if(request.getType() == null)
        {
            return ResponseEntity.badRequest().body("validate your data please");
        }
        request.setPlace_id(place_id);
        RoomCategoryDTO roomCategoryDTO = new RoomCategoryDTO(request);
        RoomCategoryDTO roomCategory = roomCategoryService.getRoomCategory(id);
        if(roomCategory == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        if(roomCategory.getPlace_id() !=place_id){
            return ResponseEntity.status(403).body("you don't have permission to enter to this data");
        }
        return ResponseEntity.ok(roomCategoryService.update(id ,roomCategoryDTO));
    }

    @DeleteMapping("{place_id}/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id , @PathVariable("place_id") Long place_id){
        if(id == null || id<=0)
        {
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(place_id == null || place_id<=0)
        {
            return ResponseEntity.badRequest().body("Invalid place_id");
        }

        RoomCategoryDTO roomCategory = roomCategoryService.getRoomCategory(id) ;
        if(roomCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        if(roomCategory.getPlace_id() != place_id){
            return ResponseEntity.status(403).body("you don't have permission to enter to this data");
        }
        roomCategoryService.delete(id);
        return ResponseEntity.ok("Delete Done successfully");
    }

}

