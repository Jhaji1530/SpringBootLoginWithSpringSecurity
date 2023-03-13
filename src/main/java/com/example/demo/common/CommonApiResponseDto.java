package com.example.demo.common;

public class CommonApiResponseDto {
	private int  status;
	private String  response;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public CommonApiResponseDto(int status, String response) {
		this.status = status;
		this.response = response;
	}
	public CommonApiResponseDto() {
		super();
	}
	@Override
	public String toString() {
		return "CommonApiResponseDto [status=" + status + ", response=" + response + "]";
	}
	
	
	
}
