package com.aan.LetsRide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.DTO.LocationCoordinatesdto;
import com.aan.LetsRide.DTO.RegDriverVehicleDTO;
import com.aan.LetsRide.DTO.Vehicledetails;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.LocationIQResponse;
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
	    private  LocationIQResponseService  locationiqservice;
	    
	    @Autowired
	    private Vechilerepo vehiclerepo;
	    @Autowired
	    private DistanceService distanceService;

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
	    
		public ResponseStructure<Driver> updateDriver(double lattitude, double longitude, Long mobilenumber) {
		      Driver d = driverrepo.findByMobileNo(mobilenumber);
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


	
		
		
//		vamshi
//		public ResponseStructure<AvailableVehicleDTO> getAvailableVehiclesByCity(Long mobileno, String destinationLocation) {
//			
//			Customer c=customerRepo.findByMobileno(mobileno);
//			String Source=c.getCurrentLoc();
//			String validatedCity = locationiqservice.validateCityFromAPI(destinationLocation);
//		
//			double distance = distanceService.getDrivingDistance(
//		            sourceCoord.getLon(), sourceCoord.getLat(),
//		            destCoord.getLon(), destCoord.getLat()
//		    );
//			AvailableVehicleDTO AVD=new AvailableVehicleDTO();
//			
//			AVD.setC(c);
//			AVD.setSourceLocation(Source);
//			AVD.setDistance(distance);
//			AVD.setDestinationLocation(destinationLocation);
//			List<Vehicledetails> l= new ArrayList<Vehicledetails>();
//			List<Vehicle> list=vehiclerepo.findAvailableVehiclesBycity(Source);
//			for (Vehicle vehicle : list) {
//				Vehicledetails vd= new Vehicledetails();
//				int A=vehicle.getAveragespeed();
//				double B=vehicle.getPriceperKM();
//				double fare=B*distance;
//				double time=distance/A;
//				vd.setV(vehicle);
//				vd.setFare(fare);
//				vd.setEstimationtime(time);
//			
//				l.add(vd);
//				
//				
//			}
//			AVD.setAvailablevehicles(l);
//			ResponseStructure <AvailableVehicleDTO>  rs=new ResponseStructure<AvailableVehicleDTO>();
//			rs.setStatuscode(HttpStatus.ACCEPTED.value());
//			rs.setMessage("Available vehicles");
//			rs.setData(AVD);
//			return rs;
//		}
		
		
		
		public ResponseStructure<AvailableVehicleDTO> getAvailableVehiclesByCity(
		        Long mobileno, String destinationLocation) {

		    Customer c = customerRepo.findByMobileno(mobileno);

		    // 1. Get Source Coordinates
		    LocationCoordinatesdto sourceCoord =
		            locationService.getCoordinates(c.getCurrentLoc());

		    // 2. Get Destination Coordinates
		    LocationCoordinatesdto destCoord =
		            locationService.getCoordinates(destinationLocation);

		    // 3. Calculate real driving distance
		    double distanceKm = distanceService.getDrivingDistance(
		            sourceCoord.getLon(), sourceCoord.getLat(),
		            destCoord.getLon(), destCoord.getLat()
		    );

		    AvailableVehicleDTO AVD = new AvailableVehicleDTO();
		    AVD.setC(c);
		    AVD.setSourceLocation(sourceCoord.getDisplayName());
		    AVD.setDestinationLocation(destCoord.getDisplayName());
		    AVD.setDistance((int) distanceKm);

		    List<Vehicledetails> detailsList = new ArrayList<>();
		    List<Vehicle> vehicles = vehiclerepo.findAvailableVehiclesBycity(sourceCoord.getDisplayName());

		    for (Vehicle vehicle : vehicles) {
		        Vehicledetails vd = new Vehicledetails();

		        double fare = vehicle.getPriceperKM() * distanceKm;
		        double time = distanceKm / vehicle.getAveragespeed();

		        vd.setV(vehicle);
		        vd.setFare(fare);
		        vd.setEstimationtime(time);

		        detailsList.add(vd);
		    }

		    AVD.setAvailablevehicles(detailsList);

		    ResponseStructure<AvailableVehicleDTO> rs = new ResponseStructure<>();
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
			customer.setCurrentLoc(locationService.getCityFromCoordinates(cdto.getLatitude(),cdto.getLongutude()));
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
		}}

