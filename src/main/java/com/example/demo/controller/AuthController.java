package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AuthRequest;
import com.example.demo.response.AuthResponseDto;
import com.example.demo.service.jwt.JwtUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/test")
	public String welcome() {
		return "Welcome to Application !!";
	}

	@PostMapping("/authenticate")
	public AuthResponseDto generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {	 
			if (authRequest != null) {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
				return new AuthResponseDto(1, jwtUtil.generateToken(authRequest.getUsername()),authRequest);
			} else{
						return new AuthResponseDto(0, "Login Failed!!", null);
			}
		} catch (Exception e) {
			throw new Exception("Inavalid username/password", e);
		}
		//return jwtUtil.generateToken(authRequest.getUsername());
	}
}
