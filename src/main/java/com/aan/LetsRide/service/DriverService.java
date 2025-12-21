package com.aan.LetsRide.service;


import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.helpers.Reporter;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.ActiveBookingDTO;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.BookingDto;
import com.aan.LetsRide.DTO.BookingHistoryDto;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.DTO.Paymentresponedto;
import com.aan.LetsRide.DTO.RegDriverVehicleDTO;

import com.aan.LetsRide.DTO.RideDTO;
import com.aan.LetsRide.DTO.UPIpaymentdto;

import com.aan.LetsRide.DTO.Vehicledetails;
import com.aan.LetsRide.DTO.api.LocationRangeDTO;
import com.aan.LetsRide.entity.Booking;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.Payment;
import com.aan.LetsRide.entity.Vehicle;
import com.aan.LetsRide.exception.BookingNotFoungWithThisID;
import com.aan.LetsRide.exception.CustomerNotFoundWithMobile;
import com.aan.LetsRide.exception.CustomerNotFoundWithThisID;
import com.aan.LetsRide.exception.CustomeralreayExists;
import com.aan.LetsRide.exception.DriverNOtFoundWiththismobileNO;
import com.aan.LetsRide.repository.BookingRepo;

import com.aan.LetsRide.exception.DriveralreayExists;
import com.aan.LetsRide.exception.InvalidDestinationLocationException;
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
	    @Autowired
	    private MailService mailService;

	    public ResponseStructure<Driver> saveRegDriver(RegDriverVehicleDTO dto) {
	    	Driver driver1= driverrepo.findByMobileNo(dto.getMobileNo());
	    if(driver1!=null) {
	    	throw new DriveralreayExists("DriveralreayExists "+dto.getMobileNo());
	    }
	    	
	        
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
	        mailService.sendMail(driver.getMail(),"Driver","Driver Registration is Successfull");
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


	
		
		
		
		
		

		public ResponseStructure<AvailableVehicleDTO> getAvailableVehiclesByCity(
		        Long mobileno, String destinationLocation) {

		    // 1. Validate Customer
		    Customer customer = customerRepo.findByMobileno(mobileno);
		    if (customer == null) {
		        throw new CustomerNotFoundWithMobile(
		                "Customer not found with mobile number: " + mobileno);
		    }

		    String sourceLocation = customer.getCurrentLoc();

		    // 2. Validate Destination City
		    boolean isValidCity = locationService.validatingCity(destinationLocation);
		    if (!isValidCity) {
		        throw new InvalidDestinationLocationException(
		                "Invalid destination city: " + destinationLocation);
		    }

		    // 3. Fetch REAL distance from LocationIQ API
		    double distance = locationService.getDistanceInKM(
		            sourceLocation, destinationLocation);

		    // 4. Fetch available vehicles in source city
		    List<Vehicle> vehicles =
		            vehiclerepo.findAvailableVehiclesBycity(sourceLocation);

		    if (vehicles.isEmpty()) {
		        throw new VehiclesareNotavilabletoDestinationLocation(
		                "No vehicles available in city: " + sourceLocation);
		    }

		    // 5. Calculate fare & estimated time
		    List<Vehicledetails> vehicleDetailsList = new ArrayList<>();

		    for (Vehicle vehicle : vehicles) {

		        double fare = vehicle.getPriceperKM() * distance;
		        double estimatedTime = distance / vehicle.getAveragespeed();

		        Vehicledetails details = new Vehicledetails();
		        details.setV(vehicle);
		        details.setFare(fare);
		        details.setEstimationtime(estimatedTime);

		        vehicleDetailsList.add(details);
		    }

		    // 6. Prepare DTO
		    AvailableVehicleDTO dto = new AvailableVehicleDTO();
		    dto.setC(customer);
		    dto.setSourceLocation(sourceLocation);
		    dto.setDestinationLocation(destinationLocation);
		    dto.setDistance(distance);
		    dto.setAvailablevehicles(vehicleDetailsList);

		    // 7. Wrap response
		    ResponseStructure<AvailableVehicleDTO> response =
		            new ResponseStructure<>();

		    response.setStatuscode(HttpStatus.ACCEPTED.value());
		    response.setMessage("Available vehicles fetched successfully");
		    response.setData(dto);

		    return response;
		}

		
		
		

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
			 mailService.sendMail(customer.getMail(),"subject","content");
			 return rs;
			 		
			
		}



		public ResponseStructure<ActiveBookingDTO>  Seeactivebooking(long mobileno) {
			Customer customer=customerRepo.findByMobileno(mobileno);
			if(customer==null) {
				throw new CustomerNotFoundWithMobile("Customer Not Found Mobileno:"+mobileno);
			}
			
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


		public ResponseStructure<BookingHistoryDto> seeBookingHistory(long mobileNo) {
		 Driver driver=driverrepo.findByMobileNo(mobileNo);
		 List<Booking> bList=driver.getBookinglist();
		 List<RideDTO> rideDTOs=new ArrayList<RideDTO>();
		 double total=0;
		 for(Booking b : bList) {
			RideDTO rideDTO=new RideDTO(b.getSourceLocation(), b.getDestinationLocation(), b.getDistanceTravelled(), b.getFare());
			total+=b.getFare();
			rideDTOs.add(rideDTO);			
		}
		 BookingHistoryDto bookingHistoryDto=new BookingHistoryDto(rideDTOs, total);
		 ResponseStructure<BookingHistoryDto> rs= new ResponseStructure<BookingHistoryDto>();
		 rs.setData(bookingHistoryDto);
		 rs.setMessage("booking history");
		 rs.setStatuscode(HttpStatus.FOUND.value());
		 
		 return rs;
		}




    



		public ResponseStructure<BookingHistoryDto> seeBookingHistoryOfCustmer(long mobileNO) {
			Customer customer=customerRepo.findByMobileno(mobileNO);
			 List<Booking> bList=customer.getBookinglist();
			 List<RideDTO> rideDTOs=new ArrayList<RideDTO>();
			 double total=0;
			 for(Booking b : bList) {
				RideDTO rideDTO=new RideDTO(b.getSourceLocation(), b.getDestinationLocation(), b.getDistanceTravelled(), b.getFare());
				total+=b.getFare();
				rideDTOs.add(rideDTO);			
			}
			 BookingHistoryDto bookingHistoryDto=new BookingHistoryDto(rideDTOs, total);
			 ResponseStructure<BookingHistoryDto> rs= new ResponseStructure<BookingHistoryDto>();
			 rs.setData(bookingHistoryDto);
			 rs.setMessage("booking history");
			 rs.setStatuscode(HttpStatus.FOUND.value());
			 
			 return rs;
		}


		

		
		
		
		

		
		public ResponseStructure<byte[]> Saveupi(int bookingid) {
			Booking b=bookingrepo.findById(bookingid).get();
			String upiid=b.getDriver().getUpiid();
			byte[] qr=QRcode(upiid);
			UPIpaymentdto upipaydto=new UPIpaymentdto();
			upipaydto.setFare(b.getFare());
			upipaydto.setQr(qr);
			ResponseStructure<byte []> rs=new ResponseStructure<byte []>();
			rs.setStatuscode(HttpStatus.ACCEPTED.value());
			rs.setMessage("Payment Completed");
			rs.setData(qr);
			return rs;
			
		}
			public byte[] QRcode(String UPIid) {
				String UPIurl="http://api.qrserver.com/v1/create-qr-code/?size=300x300&data=upi://pay?pa="+UPIid;
		    	RestTemplate rt=new RestTemplate();
		    	byte[] qr=rt.getForObject(UPIurl,byte[].class);
				return qr;
			}
		
			public void ConfirmPaymentbyQR(int id, String paymentType) {
				confirmPayment(id,paymentType);
			
			}
		
		
		
		


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
		    Payment paymentsave  = paymentre.save(payment);
		    bookingrepo.save(booking);
		    customerRepo.save(customer);
		    vehiclerepo.save(vehicle);
            ResponseStructure<Payment> response = new ResponseStructure<>();
		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Payment confirmed successfully");
		    response.setData(paymentsave);

		    return response;
		}


		public ResponseStructure<Payment> confirmPaymentbycash(int id, String paymentType) {
			
			return confirmPayment(id,paymentType) ;
		}




		

		

		public ResponseStructure<Customer> CustomerCancelBooking(int bookingid, int customerid) {
			Customer customer=customerRepo.findById(customerid).orElseThrow(() -> new CustomerNotFoundWithThisID("customer not found by this"+customerid));
			Booking booking=bookingrepo.findById(bookingid).orElseThrow(() -> new BookingNotFoungWithThisID("booking not found by this"+bookingid));
		    double bookingFare = booking.getFare();
		    double penaltyAmount = bookingFare * 0.10;
			customer.setPenalty(customer.getPenalty()+penaltyAmount);
			
			booking.setCancellationstatus("Cancelled by customer");
			customerRepo.save(customer);
			bookingrepo.save(booking);
			ResponseStructure<Customer> rs=new ResponseStructure<Customer>();
			rs.setStatuscode(HttpStatus.ACCEPTED.value());
			rs.setMessage("customer is cancelled");
			rs.setData(customer);
			return rs;
		}







public ResponseStructure<Booking> cancellationBookingByDriver(int driverId, int bookingId) {
	int consecutiveCancelCount = 0;
	 Booking booking = bookingrepo.findByIdAndDriverId(bookingId, driverId)
         .orElseThrow(() -> new RuntimeException("Booking not found"));
           
	 List<Booking> bookingList =bookingrepo.findByDriverIdAndBookingDateOrderByBookingDateDesc(
                             driverId, LocalDateTime.now() );
	 Booking book= bookingrepo.findById(bookingId).get();
	 for (Booking b : bookingList) {
		 if(b.getBookingStatus().equals("cancel by driver")) {
			 consecutiveCancelCount ++;
			 
		 }
	 }
		 Driver d=driverrepo.findById(driverId).get();
		 if(consecutiveCancelCount >=4) {
			 d.setStatus("BOOKED");
			 book.setBookingStatus("Cancelled");
		 }else if(consecutiveCancelCount <4){
			 book.setBookingStatus("Cancelled");
		 }
		 bookingrepo.save(book);
		 driverrepo.save(d);
		 ResponseStructure<Booking> response= new ResponseStructure<>();
		 response.setStatuscode(HttpStatus.OK.value());
		 response.setData(booking);
         return response;
		 
		 
		 
	 }


         public ResponseStructure<Customer> SendotpToTheCustomer(int bookingid) {
	       Booking booking=bookingrepo.findById(bookingid).orElseThrow();
	      int otp=generateOtp();
	      booking.setOtp(otp);
	      bookingrepo.save(booking);
	      Customer customer=booking.getCust();
	      mailService.sendMail(customer.getMail(),"subject","content"+otp);
	      ResponseStructure<Customer> rs=new ResponseStructure<Customer>();
	      rs.setStatuscode(HttpStatus.ACCEPTED.value());
	      rs.setMessage("otp is send succfully");
	      rs.setData(customer);
	      return rs;
         } 
	 
         

         public static int generateOtp() {
         return (int)(Math.random() * 9000) + 1000; 
        
         }


		 public ResponseStructure<Booking> VerifyotpAndStartRide(int bookingid, int otp) {
			Booking booking=bookingrepo.findById(bookingid).orElseThrow();
			
		    if (booking.getOtp() != otp) {
		        throw new RuntimeException("Invalid OTP");
		    }
		    
		    booking.setOtpverified(true);
		    booking.setBookingStatus("on Going");
		    bookingrepo.save(booking);
		    ResponseStructure<Booking> rs=new ResponseStructure<Booking>();
		    rs.setStatuscode(HttpStatus.OK.value());
		    rs.setData(booking);
			return rs;
		 }
        	

	 

	 
	
}

		

 

		


