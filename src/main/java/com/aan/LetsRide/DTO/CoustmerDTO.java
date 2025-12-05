package com.aan.LetsRide.DTO;

public class CoustmerDTO {
	private String name;
	private int age;
	private String gender;
	private long mobileno;
	private String email;
	private double latitude;
	private double longutude;
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
	public double getLongutude() {
		return longutude;
	}
	public void setLongutude(double longutude) {
		this.longutude = longutude;
	}
	@Override
	public String toString() {
		return "CoustmerDTO [name=" + name + ", age=" + age + ", gender=" + gender + ", mobileno=" + mobileno
				+ ", email=" + email + ", latitude=" + latitude + ", longutude=" + longutude + "]";
	}
	public CoustmerDTO(String name, int age, String gender, long mobileno, String email, double latitude,
			double longutude) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobileno = mobileno;
		this.email = email;
		this.latitude = latitude;
		this.longutude = longutude;
	}
	public CoustmerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
