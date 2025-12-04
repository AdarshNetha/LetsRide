package com.aan.LetsRide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aan.LetsRide.repository.DriverRepository;

@Service
public class DriverService {
	@Autowired
private DriverRepository driverrepo;
}
