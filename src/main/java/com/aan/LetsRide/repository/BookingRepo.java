package com.aan.LetsRide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aan.LetsRide.entity.Booking;
@Repository
public interface BookingRepo  extends JpaRepository<Booking, Integer>{
	

}
