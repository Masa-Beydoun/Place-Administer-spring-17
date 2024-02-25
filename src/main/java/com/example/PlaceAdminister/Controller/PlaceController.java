package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.PlaceDTO;

import com.example.PlaceAdminister.Request.PlaceRequest;
import com.example.PlaceAdminister.Service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;


    @GetMapping("allplaces")
    public ResponseEntity getAllPlacess() {
        List<PlaceDTO> placeList  = placeService.getAllPlaces();
        if(placeList == null || placeList.isEmpty() ){
            return ResponseEntity.status(200).body("there is no Place yet");
        }
        return ResponseEntity.ok(placeList);
    }

    @PostMapping("newplace")
    public ResponseEntity addPlace(@RequestBody PlaceRequest placeRequest) {
        if(placeRequest.getLocations() == null || placeRequest.getLocations().isEmpty() || placeRequest.getName() == null){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        PlaceDTO placeDTO = new PlaceDTO(placeRequest);
        PlaceDTO place = placeService.store(placeDTO);
        if(place == null){
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
        }
        return ResponseEntity.ok(place);
    }



    @GetMapping("show/{id}")
    public ResponseEntity show(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }

        PlaceDTO placeDTO = placeService.getById(id);
        if (placeDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        return ResponseEntity.ok(placeDTO);
    }

    @PutMapping ("edit/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody PlaceRequest request){
        if(id == null || id<=0)
        {
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(request.getName() == null || request.getLocations() == null || request.getLocations().isEmpty()){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        PlaceDTO placeDTO = new PlaceDTO(request);
        PlaceDTO place = placeService.getById(id);
        if(place == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        return ResponseEntity.ok(placeService.update(id ,placeDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }

        PlaceDTO placeDTO = placeService.getById(id);
        if(placeDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        placeService.delete(id);
        return ResponseEntity.ok("delete done successfully");
    }

}
