package com.aan.LetsRide.DTO;




public class RegDriverVehicleDTO {
private Long licenceNo;
private String upiid;
private String name;
private int age;
private Long mobileNo;
private String gender;
private String mail;
private String vehilename;
private String vehileno;
private String type;
private String model;
private String capacity;
private double longitude;
private double lattitude;
private double priceperKM;
public Long getLicenceNo() {
	return licenceNo;
}
public void setLicenceNo(Long licenceNo) {
	this.licenceNo = licenceNo;
}
public String getUpiid() {
	return upiid;
}
public void setUpiid(String upiid) {
	this.upiid = upiid;
}
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
public Long getMobileNo() {
	return mobileNo;
}
public void setMobileNo(Long mobileNo) {
	this.mobileNo = mobileNo;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public String getVehilename() {
	return vehilename;
}
public void setVehilename(String vehilename) {
	this.vehilename = vehilename;
}
public String getVehileno() {
	return vehileno;
}
public void setVehileno(String vehileno) {
	this.vehileno = vehileno;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getCapacity() {
	return capacity;
}
public void setCapacity(String capacity) {
	this.capacity = capacity;
}
public double getLongitude() {
	return longitude;
}
public void setLongitude(double longitude) {
	this.longitude = longitude;
}
public double getLattitude() {
	return lattitude;
}
public void setLattitude(double lattitude) {
	this.lattitude = lattitude;
}
public double getPriceperKM() {
	return priceperKM;
}
public void setPriceperKM(double priceperKM) {
	this.priceperKM = priceperKM;
}
public RegDriverVehicleDTO(Long licenceNo, String upiid, String name, int age, Long mobileNo, String gender,
		String mail, String vehilename, String vehileno, String type, String model, String capacity, double longitude,
		double lattitude, double priceperKM) {
	super();
	this.licenceNo = licenceNo;
	this.upiid = upiid;
	this.name = name;
	this.age = age;
	this.mobileNo = mobileNo;
	this.gender = gender;
	this.mail = mail;
	this.vehilename = vehilename;
	this.vehileno = vehileno;
	this.type = type;
	this.model = model;
	this.capacity = capacity;
	this.longitude = longitude;
	this.lattitude = lattitude;
	this.priceperKM = priceperKM;
}
public RegDriverVehicleDTO() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "RegDriverVehicleDTO [licenceNo=" + licenceNo + ", upiid=" + upiid + ", name=" + name + ", age=" + age
			+ ", mobileNo=" + mobileNo + ", gender=" + gender + ", mail=" + mail + ", vehilename=" + vehilename
			+ ", vehileno=" + vehileno + ", type=" + type + ", model=" + model + ", capacity=" + capacity
			+ ", longitude=" + longitude + ", lattitude=" + lattitude + ", priceperKM=" + priceperKM + "]";
}

}
