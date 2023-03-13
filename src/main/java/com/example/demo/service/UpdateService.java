package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.common.CommonApiResponseDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UpdateService {

	@Autowired
	private UserRepository userRepository;

	// Update User
	public CommonApiResponseDto updateProfile(User user) {
		System.out.println("Inside Update User method!!");
		try {
			if (user != null) {
				User ud = userRepository.findByUserId(user.getUserId());
				if (ud != null ) {
					BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
					String encPass = bc.encode(user.getPassword());
					user.setUsername(ud.getUsername());
					user.setPassword(encPass);
					userRepository.save(user);
					return new CommonApiResponseDto(1,"Profile Updated");
				} else {
					return new CommonApiResponseDto(0,"User Not found");
				}
			} else {
				return new CommonApiResponseDto(0,"Invalid data");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new CommonApiResponseDto(0,"Operation Failed");
		}
	}
}
