package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Repository.RoomCategoryRepository;
import com.example.PlaceAdminister.Request.RoomRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoomDTO extends AbstractDTO{
    private Long id;
    private Integer max_num_of_chairs;
    private Integer status;
    private Long placeId;

    public RoomDTO(RoomRequest newRoomRequest) {
        this.status = newRoomRequest.getStatus();
        this.placeId=newRoomRequest.getPlaceId();
        this.max_num_of_chairs=newRoomRequest.getMax_num_of_chairs();
    }

}
