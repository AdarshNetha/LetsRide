package com.aan.LetsRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.BookingHistoryDto;
import com.aan.LetsRide.DTO.RegDriverVehicleDTO;
import com.aan.LetsRide.entity.Booking;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.Payment;
import com.aan.LetsRide.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {
@Autowired
private DriverService driverservice;



@GetMapping("/driver/{mobileNo}")
public  ResponseStructure<Driver>findDriver(@PathVariable long mobileNo){
	return driverservice.findDriver(mobileNo);	
}

@PostMapping("/driver/update")
public ResponseStructure<Driver> updatemobilebylocation(@RequestParam double lattitude,@RequestParam double longitude,@RequestParam Long mobileNo) {
	
	return driverservice.updateDriver(lattitude,longitude,mobileNo);
	
}

@DeleteMapping("/driver/deleteByPhoneNO")
public ResponseStructure<Driver> delete(@RequestParam long mobileNo)
{
	return driverservice.deleteById(mobileNo);
}



@PostMapping("/driver/seeAllbooking")
public ResponseStructure<BookingHistoryDto> seeBookingHistory(@RequestParam long mobileNo)
{
	return driverservice.seeBookingHistory(mobileNo);
}







@GetMapping("/driver/cancelbooking")
public ResponseStructure<Booking> cancelBooking(@RequestParam int DriverId,@RequestParam int bookingId){
	return driverservice.cancellationBookingByDriver(DriverId,bookingId);
	
	
}


@PostMapping("/driver/paybyupi")
public ResponseStructure<byte[]> PaybyUPI(@RequestParam int bookingid) {
	return driverservice .Saveupi(bookingid);
}

@PostMapping("/driver/paybyQR")
public void PaymentCompleted(@RequestParam int id, @RequestParam String paymentType) {
	  driverservice .ConfirmPaymentbyQR(id,paymentType);
}



@PostMapping("/driver/completedride/paybycash")
public ResponseStructure<Payment> bookingCompleted(@RequestParam int id, @RequestParam String paymentType) {
	 return driverservice .confirmPaymentbycash(id,paymentType);
}


@GetMapping("/driver/verifyotp")
public ResponseStructure<Booking> Verifyotp(@RequestParam int bookingid,@RequestParam int otp) {
	return driverservice.VerifyotpAndStartRide(bookingid,otp);
}




}






