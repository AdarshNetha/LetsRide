package com.aan.LetsRide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aan.LetsRide.entity.Userr;
@Repository
public interface Userrepo extends JpaRepository<Userr, Integer>{

	Userr findByMobileno(long mobileNo);

}
