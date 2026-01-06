package com.aan.LetsRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.ActiveBookingDTO;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.BookingDto;
import com.aan.LetsRide.DTO.BookingHistoryDto;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.entity.Booking;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Payment;
import com.aan.LetsRide.service.CustomerService;
import com.aan.LetsRide.service.DriverService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/find")
    public ResponseStructure<Customer> findCustomer(@RequestParam long mobileno) {
        return customerService.findCustomer(mobileno);
    }

    @GetMapping("/available")
    public ResponseStructure<AvailableVehicleDTO> getAvailableVehicles(
            @RequestParam long mobileno,
            @RequestParam String destinationLocation) {
        return customerService.getAvailableVehiclesByCity(mobileno, destinationLocation);
    }

    @DeleteMapping("/delete")
    public ResponseStructure<Customer> deleteCustomer(@RequestParam long mobileno) {
        return customerService.deleteBymbno(mobileno);
    }

    @PostMapping("/book/{mobileno}")
    public ResponseStructure<Booking> bookVehicle(
            @PathVariable long mobileno,
            @RequestBody BookingDto bookingdto) {
        return customerService.bookVehicle(mobileno, bookingdto);
    }
    

    @GetMapping("/active-booking/{mobileno}")
    public ResponseStructure<ActiveBookingDTO> seeActiveBooking(
            @PathVariable long mobileno) {
        return customerService.Seeactivebooking(mobileno);
    }

    @GetMapping("/booking-history")
    public ResponseStructure<BookingHistoryDto> seeBookingHistory(
            @RequestParam long mobileno) {
        return customerService.seeBookingHistoryOfCustmer(mobileno);
    }

    @PutMapping("/booking/{bookingId}/cancel")
    public ResponseStructure<Customer> cancelBooking(
            @PathVariable int bookingId,
            @RequestParam int customerId) {
        return customerService.CustomerCancelBooking(bookingId, customerId);
    }

//    @GetMapping("/pickup")
//    public ResponseStructure<Customer> pickup(@RequestParam int bookingid) {
//        return customerService.SendotpToTheCustomer(bookingid);
//    }
}
