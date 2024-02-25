package com.example.PlaceAdminister;

import com.pusher.rest.Pusher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.Collections;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

@EnableScheduling
public class PlaceAdministerApplication {

	public static void main(String[] args) {
//		createFiles("src/main/resources/Rooms.json");
//		createFiles("src/main/resources/RoomCategories.json");
		SpringApplication.run(PlaceAdministerApplication.class, args);

//		Pusher pusher = new Pusher("1753712", "2d818fcce28a85b66b67", "54ca7ad665a3f290a976");
//		pusher.setCluster("ap1");
//		pusher.setEncrypted(true);
//		pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", "hello world"));

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
