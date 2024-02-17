package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlaceService {

    @Autowired
    private PlaceEntity placeEntity;
    @Autowired
    private PlaceRepository placeRepository;
    private final String filepath = "src/main/resources/Places.json";


    public List<PlaceDTO> getAllPlaces() {
        return placeRepository.readFromJsonFile(filepath);

    }

    public PlaceDTO store(PlaceDTO placeDTO) {
        return placeRepository.writeToJsonFile(placeDTO ,this.filepath);
    }

    public PlaceDTO getById(Long id)
    {
        return placeRepository.searchDataById(id , this.filepath);
    }


    public PlaceDTO update(Long id , PlaceDTO placeDTO){
        return placeRepository.UpdateById(id ,placeDTO,this.filepath);
    }

    public void delete(Long id){
        placeRepository.deleteById(id ,this.filepath);
    }
}
