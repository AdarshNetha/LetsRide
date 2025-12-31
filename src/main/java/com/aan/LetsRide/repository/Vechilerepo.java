package com.aan.LetsRide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aan.LetsRide.entity.Vehicle;
@Repository
public interface Vechilerepo extends JpaRepository<Vehicle, Integer> {
	@Query("select v from Vehicle v where v.currentcity=:city AND v.availabilityStatus='AVAILABLE'")
	 List<Vehicle> findAvailableVehiclesBycity(@Param("city")String city);
}
