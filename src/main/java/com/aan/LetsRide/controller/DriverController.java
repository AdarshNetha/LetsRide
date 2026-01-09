package com.aan.LetsRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.ActiveDriverBookingDto;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.BookingHistoryDto;
import com.aan.LetsRide.entity.Booking;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.Payment;
import com.aan.LetsRide.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    //  Find driver profile
    @GetMapping("/{mobileNo}")
    public ResponseStructure<Driver> findDriver(@PathVariable long mobileNo) {
        return driverService.findDriver(mobileNo);
    }

    //  Update driver location
    @PutMapping("/location")
    public ResponseStructure<Driver> updateLocation(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam long mobileNo) {

        return driverService.updateDriver(latitude, longitude, mobileNo);
    }

    //  Delete driver
    @DeleteMapping("/delete")
    public ResponseStructure<Driver> delete(@RequestParam long mobileNo) {
        return driverService.deleteById(mobileNo);
    }

    //  Booking history
    @GetMapping("/booking-history")
    public ResponseStructure<BookingHistoryDto> seeBookingHistory(
            @RequestParam long mobileno) {
        return driverService.seeBookingHistory(mobileno);
    }

    //  Cancel booking
    @PutMapping("/booking/{bookingId}/cancel")
    public ResponseStructure<Booking> cancelBooking(
            @RequestParam int driverId,
            @PathVariable int bookingId) {
        return driverService.cancellationBookingByDriver(driverId, bookingId);
    }

    //  Pay by UPI
    @PostMapping("/payment/upi")
    public ResponseStructure<byte[]> payByUpi(@RequestParam int bookingId) {
        return driverService.Saveupi(bookingId);
    }

    //  Pay by QR
    @PostMapping("/payment/qr")
    public ResponseStructure<Payment> paymentByQr(
            @RequestParam int bookingId,
            @RequestParam String paymentType) {
        return driverService.ConfirmPaymentbyQR(bookingId, paymentType);
    }

    //  Pay by cash
    @PostMapping("/payment/cash")
    public ResponseStructure<Payment> paymentByCash(
            @RequestParam int bookingId,
            @RequestParam String paymentType) {
        return driverService.confirmPaymentbycash(bookingId, paymentType);
    }

    //  Verify OTP and start ride
    @PostMapping("/booking/{bookingId}/verify-otp")
    public ResponseStructure<Booking> verifyOtp(
            @PathVariable int bookingId,
            @RequestParam int otp) {
        return driverService.VerifyotpAndStartRide(bookingId, otp);
    }
    // See Available Ride
    @GetMapping("/availableBooking")
    public ResponseStructure<ActiveDriverBookingDto> getCurrentBooking(
            @RequestParam long mobileno) {
        return driverService.getCurrentBooking(mobileno);
    }
    
    @GetMapping("/AvailableDriver")
    public ResponseStructure<Driver> AvailableDriver(
            @RequestParam long mobileno,@RequestParam String status) {
        return driverService.AvailableDriver(mobileno,status);
    }
    
    
    
    
    
    
}
