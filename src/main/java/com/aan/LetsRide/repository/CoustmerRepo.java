package com.aan.LetsRide.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aan.LetsRide.entity.Coustmer;


public interface CoustmerRepo extends JpaRepository<Coustmer, Integer> {
	 Coustmer findBymobno(long mobno);
}
