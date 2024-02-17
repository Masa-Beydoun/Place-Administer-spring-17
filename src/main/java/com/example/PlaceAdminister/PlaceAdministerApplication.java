package com.example.PlaceAdminister;

import com.example.PlaceAdminister.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.File;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PlaceAdministerApplication {

	public static void main(String[] args) {
//		createFiles("src/main/resources/Rooms.json");
//		createFiles("src/main/resources/RoomCategories.json");

		SpringApplication.run(PlaceAdministerApplication.class, args);
	}
	public static void createFiles(String filePath){
//		try {
//			File file=new File(filePath);
//			file.delete();
//			file.createNewFile();
//		}catch (Exception e){
//
//		}
	}
}
