package com.aan.LetsRide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aan.LetsRide.entity.Coustmer;
import com.aan.LetsRide.entity.Vehicle;

public interface CoustmerRepo extends JpaRepository<Coustmer, Integer> {

	

}
