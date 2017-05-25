package com.liu.lprofile.server;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.liu.lprofile.server.communication.CenterServer;

@SpringBootApplication
public class LProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LProfileServiceApplication.class, args);
		try {
			new CenterServer().listener();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
