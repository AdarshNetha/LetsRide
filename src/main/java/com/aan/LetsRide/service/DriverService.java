package com.aan.LetsRide.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.RegDriverVehicleDTO;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.Vehicle;
import com.aan.LetsRide.repository.DriverRepository;


@Service
public class DriverService {

	    @Autowired
	    private DriverRepository driverrepo;

	    @Autowired
	    private LocationService locationService;

	    public ResponseStructure<Driver> saveRegDriver(RegDriverVehicleDTO dto) {

	        // Fetch city using LocationService
	        String city = locationService.getCityFromCoordinates(
	                dto.getLattitude(),
	                dto.getLongitude()
	        );

	        Driver driver = new Driver();
	        driver.setLicenceNo(dto.getLicenceNo());
	        driver.setName(dto.getName());
	        driver.setAge(dto.getAge());
	        driver.setMail(dto.getMail());
	        driver.setGender(dto.getGender());
	        driver.setMobileNo(dto.getMobileNo());
	        driver.setUpiid(dto.getUpiid());
	        

	        Vehicle vehicle = new Vehicle();
	        vehicle.setDriver(driver);
	        vehicle.setVehilename(dto.getVehilename());
	        vehicle.setVehileno(dto.getVehileno());
	        vehicle.setType(dto.getType());
	        vehicle.setModel(dto.getModel());
	        vehicle.setCapacity(dto.getCapacity());
	        vehicle.setCurrentcity(city);
	        
	        vehicle.setPriceperKM(dto.getPriceperKM());

	        driver.setVehicle(vehicle);

	        driverrepo.save(driver);

	        ResponseStructure<Driver> resp = new ResponseStructure<>();
	        resp.setStatuscode(HttpStatus.CREATED.value());
	        resp.setMessage("Driver & Vehicle saved successfully");
	        resp.setData(driver);

	        return resp;
	    }


		public ResponseStructure<Driver> findDriver(long mobileNo) {
			 Driver driver = driverrepo.findByMobileNo(mobileNo);
			 ResponseStructure<Driver> rs =new ResponseStructure<Driver>();
				
				rs.setStatuscode(HttpStatus.FOUND.value());
				rs.setMessage("Driver with monileNo " +mobileNo + "foundr succesfully");
				rs.setData(driver);
				return rs;
				
			
		}
	    


	 




	 

		public ResponseStructure<Driver> deleteById(long mobileNo)
		{
			Driver driver = driverrepo.findByMobileNo(mobileNo);
			driverrepo.delete(driver);
			ResponseStructure<Driver> rs= new ResponseStructure<Driver>();
			rs.setData(driver);
			rs.setMessage("deleted successful");
			rs.setStatuscode(HttpStatus.OK.value());
			return rs;
		}
}

	   
	



