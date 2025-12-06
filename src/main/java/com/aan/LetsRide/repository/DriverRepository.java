package com.aan.LetsRide.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.Vehicle;
@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>{
	 Driver findByMobileNo(long mobileNo);
//	  Optional<Driver> findByMobileNo(Long mobileNo);
	

}
