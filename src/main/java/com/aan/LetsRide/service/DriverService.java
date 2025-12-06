package com.aan.LetsRide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.CoustmerDTO;
import com.aan.LetsRide.DTO.RegDriverVehicleDTO;
import com.aan.LetsRide.entity.Coustmer;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.Vehicle;
import com.aan.LetsRide.exception.CustomerNotFoundWithMobile;
import com.aan.LetsRide.exception.DriverNOtFoundWiththismobileNO;
import com.aan.LetsRide.repository.CoustmerRepo;
import com.aan.LetsRide.repository.DriverRepository;
import com.aan.LetsRide.repository.Vechilerepo;



@Service
public class DriverService {

	    @Autowired
	    private DriverRepository driverrepo;
	    @Autowired
	    private CoustmerRepo coustmerRepo;

	    @Autowired
	    private LocationService locationService;
	    
	    @Autowired
	    private Vechilerepo vehiclerepo;

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
			 if(driver==null) {
				 throw new DriverNOtFoundWiththismobileNO(mobileNo);
			 }
			
			 
			 ResponseStructure<Driver> rs =new ResponseStructure<Driver>();
				
				rs.setStatuscode(HttpStatus.FOUND.value());
				rs.setMessage("Driver with monileNo " +mobileNo + "foundr succesfully");
				rs.setData(driver);
				return rs;
				
			
		}
	    
		public ResponseStructure<Driver> updateDriver(double lattitude, double longitude, Long mobilenumber) {
		      Driver d = this.driverrepo.findByMobileNo(mobilenumber);
		      String city = this.locationService.getCityFromCoordinates(lattitude, longitude);
		      Vehicle v = d.getVehicle();
		      v.setCurrentcity(city);
		      d.setVehicle(v);
		      this.driverrepo.save(d);
		      ResponseStructure<Driver> Rs = new ResponseStructure();
		      Rs.setStatuscode(HttpStatus.ACCEPTED.value());
		      Rs.setMessage("Location updated");
		      Rs.setData(d);
		      return Rs;
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


	
		
		
//		vamshi
		public ResponseStructure<List<Vehicle>> getAvailableVehiclesByCity(String city) {
			List<Vehicle> list=vehiclerepo.findAvailableVehiclesBycity(city);
			if(list.isEmpty()) {
				return new ResponseStructure<>(HttpStatus.ACCEPTED.value(),"No vehicles found",null);
			}
			return new ResponseStructure<>(HttpStatus.ACCEPTED.value(),"Available vehicles",list);
		}
		
		
		
//		vishnu
		
		
		
		
		
//		rakshitha
		public ResponseStructure<Coustmer> findCustomer(long mobileno) {
			Coustmer cust =coustmerRepo.findByMobileno(mobileno);
			 if(cust==null) {
				 throw new  CustomerNotFoundWithMobile(mobileno);
			 }
		
			      ResponseStructure<Coustmer> rs =new ResponseStructure<Coustmer>();
					
					rs.setStatuscode(HttpStatus.FOUND.value());
					rs.setMessage("customerwith mobileNo " +mobileno + "foundr succesfully");
					rs.setData(cust);
					return rs;
		}
			
		

		
		
		
		
		
		
//	adarsh	
		
		public ResponseStructure<Coustmer> registerCoustmer(CoustmerDTO cdto) {
			Coustmer coustmer=new Coustmer();
			coustmer.setName(cdto.getName());
			coustmer.setAge(cdto.getAge());
			coustmer.setGender(cdto.getGender());
			coustmer.setMobno(cdto.getMobileno());
			coustmer.setMail(cdto.getEmail());
			coustmer.setCurrentLoc(locationService.getCityFromCoordinates(cdto.getLatitude(),cdto.getLongutude()));
			coustmerRepo.save(coustmer);
			ResponseStructure<Coustmer> rs= new ResponseStructure<Coustmer>();
			rs.setData(coustmer);
			rs.setMessage("saved cousterm");
			rs.setStatuscode(HttpStatus.CREATED.value());
			return rs;
			
			
			
		}



		
		
		
}

	   
	



