package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableCategoryEntity;
import com.example.PlaceAdminister.Repository.TableCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableCategoryService {

    @Autowired
    private TableCategoryEntity tableCategoryEntity;

    @Autowired
    private TableCategoryRepository tableCategoryRepository;

    private String filepath = "src/main/resources/TableCategory.json";


    public List<TableCategoryDTO> getAllTablesCategories(){

        return tableCategoryRepository.readFromJsonFile(this.filepath);
    }

    public TableCategoryDTO store(TableCategoryDTO tableCategoryDTO){
        return tableCategoryRepository.writeToJsonFile(tableCategoryDTO ,this.filepath);
    }

    public TableCategoryDTO getTableCategory(Long id)
    {
        return tableCategoryRepository.searchDataById(id , this.filepath);
    }


    public TableCategoryDTO update(Long id , TableCategoryDTO tableCategoryDTO){
        return tableCategoryRepository.UpdateById(id ,tableCategoryDTO,this.filepath);
    }

    public void delete(Long id){
         tableCategoryRepository.deleteById(id,filepath);
    }




}
