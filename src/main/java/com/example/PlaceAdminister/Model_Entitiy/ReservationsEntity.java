package com.example.PlaceAdminister.Model_Entitiy;

import com.example.PlaceAdminister.Request.MakeReservationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationsEntity {
    @Id
    private Long id;

    @JsonProperty
    private Integer type; // 1= room , 2 = table , 3 = seat

    @JsonProperty
    private Long room_id;

    @JsonProperty
    private Long table_id;
    @JsonProperty("num_of_seats")
    private Long num_of_seats;
    @JsonProperty("time")
    private Time time;
    @JsonProperty("period_of_reservations")
    private Integer period_of_reservations;

    @JsonProperty("status")
    private Integer status; // 1 = Delayed (not now) - 2 = occupied (user take it) - 3 = waiting (time is now but user doesn't take it yet)

//    public ReservationsEntity(Long id,Integer type,Long room_id,Long table_id,Long seat_id,LocalDateTime time,Integer period_of_reservations){
//       this.id = id;
//        this.type  = type;
//        this.room_id = room_id;
//        this.table_id = table_id;
//        this. seat_id = seat_id;
//        this. time = time;
//        this. period_of_reservations = period_of_reservations;
//    }


}
