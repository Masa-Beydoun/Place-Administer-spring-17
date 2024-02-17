package com.example.PlaceAdminister.Model_Entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class RoomCategoryEntity {


    @Id
    @JsonProperty("id")
    private Long id;
    @JsonProperty("type")
    private String type;
    private Integer num_of_seats;

    private Long place_id;

    public RoomCategoryEntity(String type) {
        this.type=type;
    }


}
