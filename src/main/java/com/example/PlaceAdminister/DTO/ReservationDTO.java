package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Model_Entitiy.ReservationsEntity;
import com.example.PlaceAdminister.Request.MakeReservationRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO extends AbstractDTO {

    private Long id;

    private Integer type; // 1= room , 2 = table , 3 = seat

    private Long room_id;

    private Long table_id;

    private Long num_of_seats;

    private Time time;

    private Integer period_of_reservations;

    private Integer status; // 1 = Delayed (not now) - 2 = occupied (user take it) - 3 = waiting (time is now but user doesn't take it yet)
    private String message;


    public ReservationDTO(MakeReservationRequest request){
        id = request.getId();
        type  = request.getType();
        room_id = request.getRoom_id();
        table_id = request.getTable_id();
        num_of_seats = request.getNum_of_seats();
        time = request.getTime();
        period_of_reservations =request.getPeriod_of_reservations();
        status = 1;
    }

    public ReservationDTO(Long id ,Integer type , Long room_id , Long table_id, Long num_of_seats ,Time time, Integer period_of_reservations){
       this.id = id;
        this.type  = type;

        if(type == 1){
           this.room_id = id;
            this.table_id = 0L;
            this.num_of_seats = 0L;
        } else {   // (request.getType() == 2)
            this.table_id=id;
            this. room_id =0L;
            this. num_of_seats = num_of_seats;
        }
        this. time = time;
        this. period_of_reservations =period_of_reservations;

        status = 1;
    }

    public ReservationDTO(ReservationsEntity reservationsEntity){
        id = reservationsEntity.getId();
        type  = reservationsEntity.getType();
        room_id = reservationsEntity.getRoom_id();
        table_id = reservationsEntity.getTable_id();
        num_of_seats = reservationsEntity.getNum_of_seats();
        time = reservationsEntity.getTime();
        period_of_reservations =reservationsEntity.getPeriod_of_reservations();
    }

    public ReservationDTO(String message){
        this.message = message;
    }

}
