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

    // 1️⃣ Find driver profile
    @GetMapping("/{mobileNo}")
    public ResponseStructure<Driver> findDriver(@PathVariable long mobileNo) {
        return driverService.findDriver(mobileNo);
    }

    // 2️⃣ Update driver location
    @PutMapping("/location")
    public ResponseStructure<Driver> updateLocation(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam long mobileNo) {

        return driverService.updateDriver(latitude, longitude, mobileNo);
    }

    // 3️⃣ Delete driver
    @DeleteMapping("/delete")
    public ResponseStructure<Driver> delete(@RequestParam long mobileNo) {
        return driverService.deleteById(mobileNo);
    }

    // 4️⃣ Booking history
    @GetMapping("/booking-history")
    public ResponseStructure<BookingHistoryDto> seeBookingHistory(
            @RequestParam long mobileNo) {
        return driverService.seeBookingHistory(mobileNo);
    }

    // 5️⃣ Cancel booking
    @PutMapping("/booking/{bookingId}/cancel")
    public ResponseStructure<Booking> cancelBooking(
            @RequestParam int driverId,
            @PathVariable int bookingId) {
        return driverService.cancellationBookingByDriver(driverId, bookingId);
    }

    // 6️⃣ Pay by UPI
    @PostMapping("/payment/upi")
    public ResponseStructure<byte[]> payByUpi(@RequestParam int bookingId) {
        return driverService.Saveupi(bookingId);
    }

    // 7️⃣ Pay by QR
    @PostMapping("/payment/qr")
    public void paymentByQr(
            @RequestParam int bookingId,
            @RequestParam String paymentType) {
        driverService.ConfirmPaymentbyQR(bookingId, paymentType);
    }

    // 8️⃣ Pay by cash
    @PostMapping("/payment/cash")
    public ResponseStructure<Payment> paymentByCash(
            @RequestParam int bookingId,
            @RequestParam String paymentType) {
        return driverService.confirmPaymentbycash(bookingId, paymentType);
    }

    // 9️⃣ Verify OTP and start ride
    @PostMapping("/booking/{bookingId}/verify-otp")
    public ResponseStructure<Booking> verifyOtp(
            @PathVariable int bookingId,
            @RequestParam int otp) {
        return driverService.VerifyotpAndStartRide(bookingId, otp);
    }
}
