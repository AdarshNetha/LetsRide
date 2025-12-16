package com.aan.LetsRide.DTO;

import java.util.Arrays;

public class UPIpaymentdto {
double fare;
byte[] qr;
public double getFare() {
	return fare;
}
public void setFare(double fare) {
	this.fare = fare;
}
public byte[] getQr() {
	return qr;
}
public void setQr(byte[] qr) {
	this.qr = qr;
}
public UPIpaymentdto(double fare, byte[] qr) {
	super();
	this.fare = fare;
	this.qr = qr;
}
public UPIpaymentdto() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "UPIpaymentDTO [fare=" + fare + ", qr=" + Arrays.toString(qr) + "]";
}

}
