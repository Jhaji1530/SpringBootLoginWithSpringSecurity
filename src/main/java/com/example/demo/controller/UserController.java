package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.CommonApiResponseDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public CommonApiResponseDto userdetails(@RequestBody User user) {
		return userService.saveUser(user);	 
	}

	@GetMapping("/get/{id}")
	public Object getDetails(@PathVariable String id) {
		return userService.getDetailsById(id);
	}
//
//	@PostMapping("/login")
//	public LoginResponseDto loginDemo(@RequestBody User user) {
//		return userService.userLogin(user);
//	}

	@PostMapping("/update")
	public String pwdUpdate(@RequestBody User user) {
		return userService.updatePassword(user);
	}

	@PostMapping("/otp")
	public String mailSender(@RequestBody User user) {

		return userService.forgetPwd(user);
		// return "Mail sent successfully";
	}
	
	
//	@GetMapping("/getId/{username}")
//	public String currentUserIdByUsername(@PathVariable String username) {
//		return userService.findUserId(username);
//	}
	
	@GetMapping("/getId/{username}")
	public User currentUserIdByUsername(@PathVariable String username) {
		return userService.findUserId(username);
	}
	
	@GetMapping("/getUser/{userId}")
	public User currentUserDetail(@PathVariable String userId) {
		return userService.findUserDetail(userId);
	}

}
