package com.aan.LetsRide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aan.LetsRide.entity.Payment;
@Repository
public interface Paymentrepo extends JpaRepository<Payment, Integer>{

}
