package com.aan.LetsRide.DTO;

public class LoginDTO {
	
	 private long mobileNo;
	 private String password;
	 public long getMobileNo() {
		 return mobileNo;
	 }
	 public void setMobileNo(long mobileNo) {
		 this.mobileNo = mobileNo;
	 }
	 public String getPassword() {
		 return password;
	 }
	 public void setPassword(String password) {
		 this.password = password;
	 }
	 public LoginDTO(long mobileNo, String password) {
		super();
		this.mobileNo = mobileNo;
		this.password = password;
	 }
	 public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	 }
	 @Override
	 public String toString() {
		return "LoginDTO [mobileNo=" + mobileNo + ", password=" + password + "]";
	 }
	 
	 
}
