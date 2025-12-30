package com.aan.LetsRide.DTO;

public class CustomerDTO {
	private String name;
	private int age;
	private String gender;
	private long mobileno;
	private String email;
	private double latitude;
	private double longitude;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CustomerDTO(String name, int age, String gender, long mobileno, String email, double latitude,
			double longitude,  String password) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobileno = mobileno;
		this.email = email;
		this.latitude = latitude;
		this.longitude = longitude;
		
		
		this.password = password;
	}
	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomerDTO [name=" + name + ", age=" + age + ", gender=" + gender + ", mobileno=" + mobileno

				+ ", email=" + email + ", latitude=" + latitude + ", longitude=" + longitude 
				+ ", password=" + password + "]"
				+ ", email=" + email + ", latitude=" + latitude + ", longitude=" + longitude + ", password=" + password
				+ "]";

	}
	
	
}
