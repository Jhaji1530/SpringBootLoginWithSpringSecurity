package com.example.demo.response;

import com.example.demo.common.CommonResponseDto;
import com.example.demo.model.AuthRequest;

public class AuthResponseDto extends CommonResponseDto {

	private AuthRequest authRequest;
	public AuthRequest getAuthRequest() {
		return authRequest;
	}

	public void setAuthRequest(AuthRequest authRequest) {
		this.authRequest = authRequest;
	}

	public AuthResponseDto(int status, String response, AuthRequest authRequest) { // super();
		this.status = status;
		this.response = response;
		this.authRequest = authRequest;

	}
	

	public AuthResponseDto() {
		super();
	}

	@Override
	public String toString() {
		return "AuthResponseDto [authRequest=" + authRequest + "]";
	}

}
