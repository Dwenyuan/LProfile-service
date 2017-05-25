package com.liu.lprofile.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liu.lprofile.server.entity.MessageBucket;

@RestController
public class MessageController {

	@RequestMapping("/helloWorld")
	public String helloWorld() {
		return "hello world";
	}
	
	@RequestMapping("/getMessage")
	public String getMessage(){
		return MessageBucket.getInstance().getMessage();
	}
}
