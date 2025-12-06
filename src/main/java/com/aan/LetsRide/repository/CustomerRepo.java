package com.aan.LetsRide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aan.LetsRide.entity.Customer;


import com.aan.LetsRide.entity.Vehicle;

import com.aan.LetsRide.entity.Driver;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	Customer findByMobileno(long mobileno);
	
}