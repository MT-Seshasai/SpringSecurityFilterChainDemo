package com.example.demo.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class Version1Controller {

	@GetMapping("/hello")
	public String getHello() {
		return "Version 2 Hello";
	}
	
	@GetMapping("/welcome")
	public String getWelcome() {
		return "Version 2 Welcome";
	}
}
