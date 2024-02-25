package com.example.PlaceAdminister.Controller;


import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.Request.MakeReservationRequest;
import com.example.PlaceAdminister.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("api/v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("getAll")
    public ResponseEntity getAllReservation(){
        List<ReservationDTO> reservationsList =  reservationService.getAllReservations();
        if(reservationsList.isEmpty()){
            return ResponseEntity.status(200).body("there is no reservations yet");
        }
        return ResponseEntity.ok(reservationsList);
    }

    @PostMapping("/reservation/{id}")
    public ResponseEntity reserve(@PathVariable("id") Long id , @RequestBody MakeReservationRequest request){
        ReservationDTO reservationDTO = new ReservationDTO(id ,request.getType() ,request.getRoom_id(),request.getTable_id(),request.getNum_of_seats(),request.getTime(),request.getPeriod_of_reservations());
        if(request.getType() == null || request.getPeriod_of_reservations() == null || request.getTime() == null){
                return ResponseEntity.badRequest().body("validate your data please");
        }
        ReservationDTO reservationResult = reservationService.reserve(reservationDTO);
        if(reservationResult.getMessage() != null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(reservationResult.getMessage());
        }
        return ResponseEntity.ok(reservationResult);
    }

    @PostMapping("reservation/takeMine/{id}")
    public ResponseEntity takeMyReservation(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        return ResponseEntity.ok(reservationService.takeReserve(id));
    }
    @PutMapping("editReservation/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody MakeReservationRequest request){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(request.getType() == null || request.getPeriod_of_reservations() == null || request.getTime() == null){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        ReservationDTO reservationDTO  = new ReservationDTO(request);
        ReservationDTO reservation = reservationService.getReservation(id);
        if(reservation == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this reservation");
        }
        return ResponseEntity.ok(reservationService.update(id,reservationDTO));
    }

    @DeleteMapping("cancelReservation/{id}")
    public ResponseEntity cancelReservation(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        reservationService.cancelReservation(id);
        return ResponseEntity.ok("cancel reservation done successfully") ;
    }


}
