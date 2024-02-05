package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Request.PlaceRequest;
import com.example.PlaceAdminister.Request.RoomCategoryRequest;
import com.example.PlaceAdminister.Service.PlaceService;
import com.example.PlaceAdminister.Service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @GetMapping("allPlaces")
    public List<PlaceDTO> getAllPlacess() {
        return placeService.getAllPlaces();
    }

    @PostMapping("newPlace")
    public PlaceDTO addPlace(@RequestBody PlaceRequest placeRequest) {
        PlaceDTO placeDTO = new PlaceDTO(placeRequest);
        return placeService.store(placeDTO);
    }


}
