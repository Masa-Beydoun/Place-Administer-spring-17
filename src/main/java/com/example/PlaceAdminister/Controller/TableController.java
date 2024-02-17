package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Request.TableRequest;
import com.example.PlaceAdminister.Service.RoomService;
import com.example.PlaceAdminister.Service.TableCategoryService;
import com.example.PlaceAdminister.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private TableCategoryService tableCategoryService;


    @GetMapping("/AllTables")
    public ResponseEntity index(){
        List<TableDTO> tablesList = tableService.getAllTables();
        if(tablesList.isEmpty()){
            return ResponseEntity.status(200).body("there are no tables yet");
        }

        return ResponseEntity.ok(tablesList);
    }


    @PostMapping("/newTable")
    public ResponseEntity create(@RequestBody TableRequest request)
    {
        if(request.getRoom_id() == null ||request.getCategory_id() == null ){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        TableDTO tableDTO = new TableDTO(request);
        TableDTO newTable = tableService.store(tableDTO);
        if(newTable == null){
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
        }
        return ResponseEntity.ok(newTable);
    }

    @GetMapping("{id}")
    public ResponseEntity show(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid id");
        }
        TableDTO table = tableService.getTable(id);
        if(table == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        return ResponseEntity.ok(table);
    }
    @GetMapping("findByRoomId/{id}")
    public ResponseEntity showByRoomId(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid room id");
        }
        RoomDTO roomDTO = roomService.getItem(id);

        if(roomDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this room");
        }

        List<TableDTO> tableRoomsList =tableService.showTablesByRoomId(id);
        if(tableRoomsList == null){
            return ResponseEntity.status(200).body("there is no tables in this room yet");
        }
        return ResponseEntity.ok(tableRoomsList);
    }

    @GetMapping("findByCategoryId/{id}")
    public ResponseEntity showByCategoryId(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid room id");
        }
        TableCategoryDTO tableCategory = tableCategoryService.getTableCategory(id);
        if(tableCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this table category");
        }
        List<TableDTO> tableList = tableService.showTablesByCategoryId(id);
        if(tableList == null){
            return ResponseEntity.status(200).body("there is no tables in this category yet");
        }
        return ResponseEntity.ok(tableList) ; //
    }

    @PutMapping("update/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody TableRequest request){
        if(id == null || id<=0){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("d");
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(request.getRoom_id() == null ||request.getCategory_id() == null ){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        TableDTO tableDTO = new TableDTO(request);
        TableDTO table = tableService.getTable(id);
        if(table == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        return ResponseEntity.ok(tableService.update(id ,tableDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id ){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }

        TableDTO table = tableService.getTable(id);
        if(table == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        tableService.delete(id);
        return ResponseEntity.ok("delete Done successfully");
    }
}
