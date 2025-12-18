package com.aan.LetsRide.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aan.LetsRide.entity.Booking;

@Repository
public interface BookingRepo  extends JpaRepository<Booking, Integer>{

	Optional<Booking> findByIdAndDriverId(int bookingId, int driverId);

	List<Booking> findByDriverIdAndBookingDateOrderByBookingDateDesc(int driverId, Object setBookingDate);


	
}


