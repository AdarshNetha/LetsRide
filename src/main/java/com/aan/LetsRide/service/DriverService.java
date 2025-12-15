package com.aan.LetsRide.service;


import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.ActiveBookingDTO;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.BookingDto;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.DTO.Paymentresponedto;
import com.aan.LetsRide.DTO.RegDriverVehicleDTO;
import com.aan.LetsRide.DTO.Vehicledetails;
import com.aan.LetsRide.DTO.api.LocationRangeDTO;
import com.aan.LetsRide.entity.Booking;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.Payment;
import com.aan.LetsRide.entity.Vehicle;
import com.aan.LetsRide.exception.CustomerNotFoundWithMobile;
import com.aan.LetsRide.exception.CustomeralreayExists;
import com.aan.LetsRide.exception.DriverNOtFoundWiththismobileNO;
import com.aan.LetsRide.repository.BookingRepo;

import com.aan.LetsRide.exception.DriveralreayExists;
import com.aan.LetsRide.exception.VehiclesareNotavilabletoDestinationLocation;
import com.aan.LetsRide.repository.BookingRepo;
import com.aan.LetsRide.repository.CustomerRepo;
import com.aan.LetsRide.repository.DriverRepository;
import com.aan.LetsRide.repository.Paymentrepo;
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
	    @Autowired
	     private BookingRepo bookingrepo;
	    @Autowired
	    private Paymentrepo paymentre;

	    public ResponseStructure<Driver> saveRegDriver(RegDriverVehicleDTO dto) {
	    	Driver driver1= driverrepo.findByMobileNo(dto.getMobileNo());
	    if(driver1!=null) {
	    	throw new DriveralreayExists("DriveralreayExists "+dto.getMobileNo());
	    }
	    	
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
				 throw new DriverNOtFoundWiththismobileNO("DriverNOtFoundWiththismobileNO"+mobileNo);
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

			if(driver==null) {
				 throw new DriverNOtFoundWiththismobileNO("DriverNOtFoundWiththismobileNO"+mobileNo);
			}

			driverrepo.delete(driver);
			ResponseStructure<Driver> rs= new ResponseStructure<Driver>();
			rs.setData(driver);
			rs.setMessage("deleted successful");
			rs.setStatuscode(HttpStatus.OK.value());
			return rs;
		}


	
		
		
		
		
		
		
		
		public ResponseStructure<AvailableVehicleDTO> getAvailableVehiclesByCity(Long mobileno, String distinationLocation) {
			
			Customer c=customerRepo.findByMobileno(mobileno);
			if(c==null) {
				 throw new  CustomerNotFoundWithMobile("CustomerNotFoundWithMobile"+mobileno);

			}
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
			if(list==null) {
				throw new VehiclesareNotavilabletoDestinationLocation("No vehicles available in city"+Source);
			}
			
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
				 throw new  CustomerNotFoundWithMobile("CustomerNotFoundWithMobile"+mobileno);
			 }
		
			      ResponseStructure<Customer> rs =new ResponseStructure<Customer>();
					
					rs.setStatuscode(HttpStatus.FOUND.value());
					rs.setMessage("customerwith mobileNo " +mobileno + "foundr succesfully");
					rs.setData(cust);
					return rs;
		}
			
		

		
		
		
		
		
		
//	adarsh	
		
		public ResponseStructure<Customer> registerCustomer(CustomerDTO cdto) {

			Customer cust =customerRepo.findByMobileno(cdto.getMobileno());
			 if(cust!=null) {
				 throw new  CustomeralreayExists("CustomeralreayExists"+cdto.getMobileno());
			 }

			
		

			Customer customer=new Customer();
			
			customer.setName(cdto.getName());
			customer.setAge(cdto.getAge());
			customer.setGender(cdto.getGender());
			customer.setMobileno(cdto.getMobileno());
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
			 if(cust==null) {

				 throw new  CustomerNotFoundWithMobile("CustomerNotFoundWithMobile"+mobileno);
			 }


			customerRepo.delete(cust);
			ResponseStructure<Customer> rs= new ResponseStructure<Customer>();
			rs.setData(cust);
			rs.setMessage("delete coustmer by mobno"+mobileno);
			rs.setStatuscode(HttpStatus.CREATED.value());
			return rs;
		}


		public ResponseStructure<Booking> bookVehicle(Long mobno, BookingDto bookingdto) {
			Customer customer=customerRepo.findByMobileno(mobno);
			
			Vehicle vehicle=vehiclerepo.findById(bookingdto.getVehicleid()).orElseThrow(() -> new RuntimeException("Vehicle not found for id: " + bookingdto.getVehicleid())); 
			
			 Booking booking = new Booking();
			 booking .setCust(customer);
			 booking.setDriver(vehicle.getDriver());
			 booking.setSourceLocation(bookingdto.getSourceLocation());
			 booking.setDestinationLocation(bookingdto.getDestinationLocation());
			 booking.setFare(bookingdto.getFare());
			 booking.setEstimationTravelTime(bookingdto.getEstimationTravelTime());
			 booking.setDistanceTravelled(bookingdto.getDistanceTravelled());
			 booking.setBookingDate(LocalDateTime.now());
			 vehicle.setAvailabilityStatus("booked");

			Booking confBooking= bookingrepo.save(booking);

			
			 List<Booking> bookingList=new ArrayList<Booking>();
			 bookingList=customer.getBookinglist();
			 bookingList.add(booking);
			 customer.setBookinglist(bookingList);
			 customerRepo.save(customer);
			 
			 
			 Driver driver	= vehicle.getDriver();
			 List<Booking> driverookinglist= new ArrayList<Booking>();
			 driverookinglist= driver.getBookinglist();
			 driverookinglist.add(booking);
			 driver.setBookinglist(driverookinglist);
			 driverrepo.save(driver);
			
			 ResponseStructure<Booking> rs= new ResponseStructure<Booking>();
			 rs.setMessage("booking succesfullay");
			 rs.setStatuscode(HttpStatus.ACCEPTED.value());
			 rs.setData(confBooking);
			 
			 return rs;
			 		
			
		}



		public ResponseStructure<ActiveBookingDTO>  Seeactivebooking(long mobileno) {
			Customer customer=customerRepo.findByMobileno(mobileno);
			if(customer==null) {
				throw new CustomerNotFoundWithMobile("Customer Not Found Mobileno:"+mobileno);
			}
			
//			Booking booking=bookingrepo.findBycustmobilenoAndBookingstatus(mobileno,"pending");
//			if(booking==null){
//				throw new ActivebookingNotFoundwithcustomer("No Active Booking found for this customer");
//			}
			
			ActiveBookingDTO activebooking=new ActiveBookingDTO();
			activebooking.setCustname(customer.getName());
			activebooking.setCustmobileno(customer.getMobileno());
			activebooking.setCurrentlocation(customer.getCurrentLoc());
			
			List<Booking> bookinglist=new ArrayList<Booking>();
			bookinglist=customer.getBookinglist();
			
			ResponseStructure<ActiveBookingDTO> activebooking1=new ResponseStructure<ActiveBookingDTO>();
			for (Booking booking : bookinglist) {
				if(booking.getBookingDate().equals("pending"))
				{
					
					activebooking.setBooking(booking);
					activebooking1.setStatuscode(HttpStatus.ACCEPTED.value());
					activebooking1.setMessage("Active Booking");
					activebooking1.setData(activebooking);
				}
				else {
					activebooking.setBooking(booking);
					activebooking1.setStatuscode(HttpStatus.ACCEPTED.value());
					activebooking1.setMessage("Active Booking");
					activebooking1.setData(null);
					
					
					
				}
			}
			
			
			return activebooking1;
			

			
		}
		
		
		
		
//		vamshi
		
		
		
		
		
		
//		rakshitha
		public ResponseStructure<Payment> confirmPayment(int bookingId, String paymentType) {

		    Booking booking = bookingrepo.findById(bookingId)
		            .orElseThrow(() -> new RuntimeException("Booking not found"));

		    booking.setBookingStatus("COMPLETED");
		    booking.setPaymentStatus("PAID");
		    Customer customer = booking.getCust();
		    customer.setActiveBookingFlag(false);
		    
		    Vehicle vehicle = booking.getDriver().getVehicle();
		    vehicle.setAvailabilityStatus("AVAILABLE");
		    Payment payment = new Payment();
		    payment.setVehicle(vehicle);
		    payment.setCustomer(customer);
		    payment.setBooking(booking);
		    payment.setAmount(booking.getFare());
		    payment.setPaymentType(paymentType);
		    paymentre.save(payment);
		    bookingrepo.save(booking);
		    customerRepo.save(customer);
		    vehiclerepo.save(vehicle);
            ResponseStructure<Payment> response = new ResponseStructure<>();
		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Payment confirmed successfully");
		    response.setData(payment);

		    return response;
		}


		public ResponseStructure<Payment> confirmPaymentbycash(int id, String paymentType) {
			
			return confirmPayment(id,paymentType) ;
		}


		
 

		}

