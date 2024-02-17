package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Repository.AbstractRepository;
import com.example.PlaceAdminister.Repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {

    @Autowired
    private AbstractRepository abstractRepository;

    @Autowired
    private TableRepository tableRepository;

    private String reservationFilePath = "src/main/resources/Reservations.json";
    private String TableFilePath = "src/main/resources/Tables.json";


    public List<ReservationDTO>  getAllReservations(){
        return abstractRepository.readFromJsonReservation(reservationFilePath);
    }



    public ReservationDTO reserve(ReservationDTO reservationDTO){
        String filePath = "src/main/resources/Reservations.json";
//        System.out.println(reservationDTO.getNum_of_seats());
       List<ReservationDTO> reservationDTOList =  abstractRepository.readFromJsonReservation(filePath);

       if(reservationDTO.getType() ==1){
            ReservationDTO existedReservation=  reservationDTOList.stream()
                   .filter(i ->i.getRoom_id().equals(reservationDTO.getRoom_id()))
                    .filter(j -> (j.getTime().getHours() == reservationDTO.getTime().getHours()) || (j.getTime().getHours() + j.getPeriod_of_reservations() -1 == reservationDTO.getTime().getHours()))
                   .findFirst().orElse(null) ;
            if(existedReservation != null){
                return new ReservationDTO("Sorry, This room in this time is already reserved");
            }

       }
       else if (reservationDTO.getType() == 2){
           ReservationDTO existedReservation = reservationDTOList.stream()
                   .filter(i->i.getTable_id().equals(reservationDTO.getTable_id()))
                   .filter(j -> (j.getTime().getHours() == reservationDTO.getTime().getHours()) || (j.getTime().getHours() + j.getPeriod_of_reservations() -1 == reservationDTO.getTime().getHours()))
                   .findFirst().orElse(null);

           TableDTO tableDTO = tableRepository.searchDataById(reservationDTO.getTable_id() ,TableFilePath);
//           TableDTO tableDTO = tableDTOList.stream()
//                   .filter(i->i.getRoom_id().equals(reservationDTO.))

           ReservationDTO existedRoomReservation = reservationDTOList.stream()
                   .filter(i->i.getRoom_id().equals(tableDTO.getRoom_id()))
                   .filter(j -> (j.getTime().getHours() == reservationDTO.getTime().getHours()) || (j.getTime().getHours() + j.getPeriod_of_reservations() -1 == reservationDTO.getTime().getHours()))
                   .findFirst().orElse(null);

           if(existedReservation != null){
               return new ReservationDTO("Sorry, this table is already reserved in this time");
           }
           if (existedRoomReservation != null){
               return new ReservationDTO("Sorry, room that you want to reserve in is already reserved in this time");
           }
       }


       // this case 3 did not hand perfectly
       if(reservationDTO.getType() == 3){
           // if table is reserved..
           ReservationDTO existedReservation = reservationDTOList.stream()
                   .filter(i->i.getTable_id().equals(reservationDTO.getTable_id()))
                   .filter(j -> j.getTime().getHours() == reservationDTO.getTime().getHours())
                   .findFirst().orElse(null);

           // if number of available seats is enough
           TableDTO tableDTO = tableRepository.searchDataById(reservationDTO.getTable_id() ,TableFilePath);
//           TableDTO tableDTO = tableDTOList.stream()
//                   .filter(i->i.getRoom_id().equals(reservationDTO.))

           ReservationDTO enoughSeatsCheck = reservationDTOList.stream()
                                                    //table size            -    available seats on this table  = num of available size
                   .filter(i->i.getNum_of_seats() <= tableDTO.getAvailable_seats().size())
//                           - tableDTO.getAvailable_seats()
//                           .stream().filter(j->j.equals(false)).count())
                   // in this time
                   .filter(x -> x.getTime().getHours() == reservationDTO.getTime().getHours())
                   .findFirst().orElse(null);


           // maxofSeats on this table - existedReservation.getNum_of_seats() < reservationDTO.getNum_of_seats()
           if(existedReservation != null){
               return new ReservationDTO("Sorry, this table is already reserved in this time");
           }
           if (enoughSeatsCheck != null){
               return new ReservationDTO("Sorry, this table is only available for limited seats");
           }

       }
       ReservationDTO r = abstractRepository.reserveTable(reservationDTO,filePath);
        return r;
//

    }
    public ReservationDTO update(Long id , ReservationDTO reservationDTO){
        return abstractRepository.UpdateById(id ,reservationDTO, reservationFilePath);
    }

    public ReservationDTO getReservation(Long id){
        return abstractRepository.findDataById(id ,reservationFilePath);
    }

    public String cancelReservation(Long id){
        abstractRepository.deleteById(id ,reservationFilePath);
        return "Deleted Done successfully";
    }


}
