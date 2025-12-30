package com.aan.LetsRide.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.ActiveBookingDTO;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.BookingDto;
import com.aan.LetsRide.DTO.BookingHistoryDto;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.DTO.RideDTO;
import com.aan.LetsRide.DTO.Vehicledetails;
import com.aan.LetsRide.entity.Booking;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.entity.Userr;
import com.aan.LetsRide.entity.Vehicle;
import com.aan.LetsRide.exception.BookingNotFoungWithThisID;
import com.aan.LetsRide.exception.CustomerNotFoundWithMobile;
import com.aan.LetsRide.exception.CustomerNotFoundWithThisID;
import com.aan.LetsRide.exception.CustomeralreayExists;
import com.aan.LetsRide.exception.InvalidDestinationLocationException;
import com.aan.LetsRide.exception.VehiclesareNotavilabletoDestinationLocation;
import com.aan.LetsRide.repository.BookingRepo;
import com.aan.LetsRide.repository.CustomerRepo;
import com.aan.LetsRide.repository.DriverRepository;
import com.aan.LetsRide.repository.Paymentrepo;
import com.aan.LetsRide.repository.Userrepo;
import com.aan.LetsRide.repository.Vechilerepo;

@Service
public class CustomerService {
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
		
		Userr userr=new Userr();
		userr.setMobileno(cdto.getMobileno());
		String encodedPassword = passwordEncoder.encode(cdto.getPassword());
		userr.setPassword(encodedPassword);
		userr.setRole("Customer");
		userrepo.save(userr);
		customerRepo.save(customer);
		ResponseStructure<Customer> rs= new ResponseStructure<Customer>();
		rs.setData(customer);
		rs.setMessage("saved cousterm");
		rs.setStatuscode(HttpStatus.CREATED.value());
		return rs;
		
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
		 int otp=generateOtp();
	      booking.setOtp(otp);

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
		 mailService.sendMail(customer.getMail(),"Booking Detials","Dear "+confBooking.getCust().getName()+", /n Thankyou for choosing LetsRideyou. /n  You have booked a vechile /n from"+confBooking.getSourceLocation()+"/n to"+confBooking.getDestinationLocation()+"/n on date:"+confBooking.getBookingDate()+"/n Booking id:" +confBooking.getId()+"/n Diver Name"+confBooking.getDriver().getName()+"/n Driver phoneNo :"+confBooking.getDriver().getMobileNo()+"/n Vehicle No :"+confBooking.getDriver().getVehicle().getVehileno()+"/n Fair :"+confBooking.getFare()+"/n Distance  :"+confBooking.getDistanceTravelled());
		 mailService.sendMail(driver.getMail(), "Booking Detials", "Dear"+confBooking.getDriver().getName()+"/n You have a booking on  "+confBooking.getBookingDate()+"/n You have booked a vehicle /n from"+confBooking.getSourceLocation()+"/n to"+confBooking.getDestinationLocation()+"/n Booking id "+confBooking.getId()+"/n Customer name :"+confBooking.getCust().getName()+"/n Custmoer MobileNo :"+confBooking.getCust().getMobileno()+"/n Distance"+confBooking.getDistanceTravelled()+"/n Fair :"+confBooking.getFare());
		 return rs;
		 		
		
	}

	 public static int generateOtp() {
         return (int)(Math.random() * 9000) + 1000; 
        
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
	 public ResponseStructure<Customer> SendotpToTheCustomer(int bookingid) {
	       Booking booking=bookingrepo.findById(bookingid).orElseThrow();
	      int otp=generateOtp();
	      booking.setOtp(otp);
	      bookingrepo.save(booking);
	      Customer customer=booking.getCust();
	      mailService.sendMail(customer.getMail(),"subject","content"+otp);
	      ResponseStructure<Customer> rs=new ResponseStructure<Customer>();
	      rs.setStatuscode(HttpStatus.ACCEPTED.value());
	      rs.setMessage("otp is send success fully");
	      rs.setData(customer);
	      return rs;
       } 
	 

}
