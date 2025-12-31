package com.aan.LetsRide.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.ActiveBookingDTO;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.BookingDto;
import com.aan.LetsRide.DTO.BookingHistoryDto;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.DTO.RegDriverVehicleDTO;
import com.aan.LetsRide.DTO.RideDTO;
import com.aan.LetsRide.DTO.UPIpaymentdto;
import com.aan.LetsRide.DTO.Vehicledetails;
import com.aan.LetsRide.entity.Booking;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.Payment;
import com.aan.LetsRide.entity.Userr;
import com.aan.LetsRide.entity.Vehicle;
import com.aan.LetsRide.exception.BookingNotFoungWithThisID;
import com.aan.LetsRide.exception.CustomerNotFoundWithMobile;
import com.aan.LetsRide.exception.CustomerNotFoundWithThisID;
import com.aan.LetsRide.exception.CustomeralreayExists;
import com.aan.LetsRide.exception.DriverNOtFoundWiththismobileNO;
import com.aan.LetsRide.exception.DriveralreayExists;
import com.aan.LetsRide.exception.InvalidDestinationLocationException;
import com.aan.LetsRide.exception.VehiclesareNotavilabletoDestinationLocation;
import com.aan.LetsRide.repository.BookingRepo;
import com.aan.LetsRide.repository.CustomerRepo;
import com.aan.LetsRide.repository.DriverRepository;
import com.aan.LetsRide.repository.Paymentrepo;
import com.aan.LetsRide.repository.Userrepo;
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
	    @Autowired 
	    private Userrepo userrepo;
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    public ResponseStructure<Driver> saveRegDriver(RegDriverVehicleDTO dto) {
	    	Driver driver1= driverrepo.findByMobileNo(dto.getMobileNo());
	    if(driver1!=null) {
	    	throw new DriveralreayExists("DriveralreayExists "+dto.getMobileNo());
	    }
	    Userr existingUser = userrepo.findByMobileno(dto.getMobileNo());
	    if (existingUser != null) {
	        throw new RuntimeException(
	            "User already exists with mobile no: " + dto.getMobileNo()
	        );
	    }

	        
	        String city = locationService.getCityFromCoordinates(dto.getLattitude(),dto.getLongitude());
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

	
	        Userr userr=new Userr();
	        userr.setRole("DRIVER");
	        userr.setMobileno(dto.getMobileNo());
	        String encodedPassword = passwordEncoder.encode(dto.getPassword());
	        userr.setPassword(encodedPassword);
	        driver.setUserr(userr);
	        driver.setVehicle(vehicle);
	        userrepo.save(userr);
	        driverrepo.save(driver);

	        ResponseStructure<Driver> response = new ResponseStructure<>();
	        response.setStatuscode(HttpStatus.CREATED.value());
	        response.setMessage("Driver & user & Vehicle saved successfully");
	        response.setData(driver);
	        mailService.sendMail(driver.getMail(),"Driver","Driver Registration is Successfull");

	        return response;
	    }

	    


		public ResponseStructure<Driver> findDriver(long mobileNo) {
			 Driver driver = driverrepo.findByMobileNo(mobileNo);
			 if(driver==null) {
				 throw new DriverNOtFoundWiththismobileNO("DriverNOtFoundWiththismobileNO"+mobileNo);
			 }
			
			 
			 ResponseStructure<Driver> rs =new ResponseStructure<Driver>();
				
				rs.setStatuscode(HttpStatus.FOUND.value());
				rs.setMessage("Driver with mobileNo " +mobileNo + "found succesfully");
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
		      ResponseStructure<Driver> Rs = new ResponseStructure<>();
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

//OTP Generated
        
         

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

		

 

		


