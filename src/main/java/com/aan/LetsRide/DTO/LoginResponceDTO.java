package com.aan.LetsRide.DTO;

public class LoginResponceDTO {
	
	private String barrierToken;
	private String role;
	@Override
	public String toString() {
		return "LoginResponceDTO [barrierToken=" + barrierToken + ", role=" + role + "]";
	}
	public LoginResponceDTO(String barrierToken, String role) {
		super();
		this.barrierToken = barrierToken;
		this.role = role;
	}
	public LoginResponceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBarrierToken() {
		return barrierToken;
	}
	public void setBarrierToken(String barrierToken) {
		this.barrierToken = barrierToken;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
