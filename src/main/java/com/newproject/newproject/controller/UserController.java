package com.newproject.newproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.newproject.newproject.entity.UserEntity;
import com.newproject.newproject.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
   
	@PostMapping("/newuser")
	public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity)
	{
		Boolean flag = userService.saveUser(userEntity);
		if(flag) 
		{
		return new ResponseEntity<String>("User registered successfully",HttpStatus.OK);
		}
		return new ResponseEntity<String>("User isn't registered successfully",HttpStatus.CONFLICT);
    }
	
	@GetMapping("/loginuser")
	public ResponseEntity<String> loginUser(@RequestParam("username") String username, @RequestParam("password")String password)
	{
		UserEntity userEntity = userService.loginUser(username);
		if(userEntity.getUserPassword()==password)
		{   
			return new ResponseEntity<String>("logged in successfully",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Please check your credentials",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping("/test")
	public String test()
	{
		System.out.println("we are in the test");
		return "NewFile";
	}
	
}