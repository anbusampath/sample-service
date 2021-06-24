package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	List<User> users = new ArrayList<>();

	@PostMapping("user")
	public User saveUser(@RequestBody User user) {
		users.add(user);
		return user;
		
	}
	
	@GetMapping("users")
	public List<User> getUsers(){
		return users;
	}
}
