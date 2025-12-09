package com.aan.LetsRide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.DTO.RegDriverVehicleDTO;
import com.aan.LetsRide.DTO.Vehicledetails;
import com.aan.LetsRide.DTO.api.LocationRangeDTO;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.Vehicle;
import com.aan.LetsRide.exception.CustomerNotFoundWithMobile;
import com.aan.LetsRide.exception.DriverNOtFoundWiththismobileNO;
import com.aan.LetsRide.repository.CustomerRepo;
import com.aan.LetsRide.repository.DriverRepository;
import com.aan.LetsRide.repository.Vechilerepo;



@Service
public class DriverService {

	    @Autowired
	    private DriverRepository driverrepo;
	    @Autowired
	    private CustomerRepo customerRepo;

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
	        vehicle.setAveragespeed(dto.getAveragespeed());
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
	    
		public ResponseStructure<Driver> updateDriver(double lattitude, double longitude, Long mobileNo) {
		      Driver d = driverrepo.findByMobileNo(mobileNo);
		      String city = locationService.getCityFromCoordinates(lattitude, longitude);
		      Vehicle v = d.getVehicle();
		      v.setCurrentcity(city);
		      d.setVehicle(v);
		       driverrepo.save(d);
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


	
		
		
		
		
		
		
		
		public ResponseStructure<AvailableVehicleDTO> getAvailableVehiclesByCity(Long mobileno, String distinationLocation) {
			
			Customer c=customerRepo.findByMobileno(mobileno);
			String Source=c.getCurrentLoc();
			String DistinationLocation=distinationLocation;
			boolean check=locationService.validatingCity(DistinationLocation);
			if(!check)
			{
				System.out.println("false location");
				return null;
			}
			
			
			LocationRangeDTO locationRangeDTO=locationService.getFromAndToCoordinates(Source, DistinationLocation);
			
			System.err.println(locationRangeDTO);
			int distance=200;
			AvailableVehicleDTO AVD=new AvailableVehicleDTO();
			
			AVD.setC(c);
			AVD.setSourceLocation(Source);
			AVD.setDistance(distance);
			AVD.setDestinationLocation(DistinationLocation);
			List<Vehicledetails> l= new ArrayList<Vehicledetails>();
			List<Vehicle> list=vehiclerepo.findAvailableVehiclesBycity(Source);
			for (Vehicle vehicle : list) {
				Vehicledetails vd= new Vehicledetails();
				int A=vehicle.getAveragespeed();
				double B=vehicle.getPriceperKM();
				double fare=B*distance;
				double time=distance/A;
				vd.setV(vehicle);
				vd.setFare(fare);
				vd.setEstimationtime(time);
			
				l.add(vd);
				
				
			}
			AVD.setAvailablevehicles(l);
			ResponseStructure <AvailableVehicleDTO>  rs=new ResponseStructure<AvailableVehicleDTO>();
			rs.setStatuscode(HttpStatus.ACCEPTED.value());
			rs.setMessage("Available vehicles");
			rs.setData(AVD);
			return rs;

		}
		
		
		
//		vishnu
		
		
		
		
		
//		rakshitha
		public ResponseStructure<Customer> findCustomer(long mobileno) {
			Customer cust =customerRepo.findByMobileno(mobileno);
			 if(cust==null) {
				 throw new  CustomerNotFoundWithMobile(mobileno);
			 }
		
			      ResponseStructure<Customer> rs =new ResponseStructure<Customer>();
					
					rs.setStatuscode(HttpStatus.FOUND.value());
					rs.setMessage("customerwith mobileNo " +mobileno + "foundr succesfully");
					rs.setData(cust);
					return rs;
		}
			
		

		
		
		
		
		
		
//	adarsh	
		
		public ResponseStructure<Customer> registerCustomer(CustomerDTO cdto) {
			Customer customer=new Customer();
			customer.setName(cdto.getName());
			customer.setAge(cdto.getAge());
			customer.setGender(cdto.getGender());
			customer.setMobno(cdto.getMobileno());
			customer.setMail(cdto.getEmail());
			customer.setCurrentLoc(locationService.getCityFromCoordinates(cdto.getLatitude(),cdto.getLongitude()));
			customerRepo.save(customer);
			ResponseStructure<Customer> rs= new ResponseStructure<Customer>();
			rs.setData(customer);
			rs.setMessage("saved cousterm");
			rs.setStatuscode(HttpStatus.CREATED.value());
			return rs;
			
			
			
		}



		public ResponseStructure<Customer> deleteBymbno(long mobileno) {
			
			Customer cust= customerRepo.findByMobileno(mobileno);
			customerRepo.delete(cust);
			ResponseStructure<Customer> rs= new ResponseStructure<Customer>();
			rs.setData(cust);
			rs.setMessage("delete coustmer by mobno"+mobileno);
			rs.setStatuscode(HttpStatus.CREATED.value());
			return rs;
		}


		
		}

