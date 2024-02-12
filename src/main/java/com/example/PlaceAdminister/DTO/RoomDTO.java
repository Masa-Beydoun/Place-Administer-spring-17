package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Repository.RoomCategoryRepository;
import com.example.PlaceAdminister.Request.RoomRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
@NoArgsConstructor
public class RoomDTO extends AbstractDTO{
    private Long id;
    private Integer max_num_of_chairs;
    private Integer status;
    private Long placeId;
    private Long category_id;

    private String message;

    public RoomDTO(RoomRequest newRoomRequest) {
        this.status = newRoomRequest.getStatus();
        this.category_id =newRoomRequest.getCategory_id();
        this.placeId=newRoomRequest.getPlaceId();
        this.max_num_of_chairs=newRoomRequest.getMax_num_of_chairs();
    }

    public RoomDTO(String message){
        this.message = message;
    }

}
