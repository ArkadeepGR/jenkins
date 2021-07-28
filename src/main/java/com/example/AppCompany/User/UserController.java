package com.example.AppCompany.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/user/{id}")
	public User1 getUser(@PathVariable long id){
		return userService.getUserById(id);
	}
	
	@PostMapping("/users/signup")
	public User1 newUser(@RequestBody User1 req) {
		return userService.saveNewUser(req);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PatchMapping("/users")
	public User1 updateUser(@RequestBody User1 req) {
		return userService.saveUpdatedUser(req);
	}
		
	@GetMapping("/confirm-user/{id}")
	public String confirmUser(@PathVariable long id) {
		return userService.setConfirmUser(id);
	}

}
