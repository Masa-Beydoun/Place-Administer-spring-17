package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.Request.TableCategoryRequest;
import com.example.PlaceAdminister.Service.TableCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("api/v1/table-category")
public class TableCategoryController {

    @Autowired
    private TableCategoryService tableCategoryService;

    @GetMapping("/AllTables")
    public ResponseEntity index(){
        List<TableCategoryDTO> tableCategoryList = tableCategoryService.getAllTablesCategories();
        if(tableCategoryList.isEmpty()){
            return ResponseEntity.status(200).body("there is no table category yet");
        }
        return ResponseEntity.ok(tableCategoryList);
    }

    @PostMapping("/newTable")
    public ResponseEntity create(@RequestBody TableCategoryRequest request)
    {
        if(request.getShape() == null || request.getNum_of_seats() == null ){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        TableCategoryDTO tableDTO = new TableCategoryDTO(request);
        TableCategoryDTO newTableCategory = tableCategoryService.store(tableDTO);
        if(newTableCategory == null){
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
        }
        return ResponseEntity.ok(newTableCategory) ;
    }
    @GetMapping("{id}")
    public ResponseEntity show(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        TableCategoryDTO tableCategory = tableCategoryService.getTableCategory(id);
        if(tableCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        return ResponseEntity.ok(tableCategory) ;
    }

    @PutMapping("update/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody TableCategoryRequest request){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(request.getShape() == null || request.getNum_of_seats() == null ){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        TableCategoryDTO tableDTO = new TableCategoryDTO(request);
        TableCategoryDTO tableCategory = tableCategoryService.getTableCategory(id);
        if(tableCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        return ResponseEntity.ok(tableCategoryService.update(id ,tableDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        TableCategoryDTO tableCategory = tableCategoryService.getTableCategory(id);
        if(tableCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        tableCategoryService.delete(id);
        return ResponseEntity.ok("Delete Done successfully");
    }

}
