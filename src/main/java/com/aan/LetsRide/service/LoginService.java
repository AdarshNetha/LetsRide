package com.aan.LetsRide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.LoginDTO;
import com.aan.LetsRide.DTO.LoginResponceDTO;
import com.aan.LetsRide.Security.JwtUtils;
import com.aan.LetsRide.entity.Userr;
import com.aan.LetsRide.repository.Userrepo;

@Service
public class LoginService {
	 @Autowired
	    private JwtUtils jwtUtils;
	 @Autowired
	    private AuthenticationManager authenticationManager;
	 @Autowired
	private Userrepo userrRepository;
	
	 public ResponseEntity<ResponseStructure<LoginResponceDTO>> login(LoginDTO dto) {

	        // Authenticate using Spring Security
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(String.valueOf(dto.getMobileNo()),dto.getPassword()));

	        // Fetch user
	        Userr user = userrRepository.findByMobileno(dto.getMobileNo());
	        
	               if(user==null) { 
	            	   throw new RuntimeException("User not found");
	            	   }

	        // Generate JWT
	        String token = jwtUtils.generateToken(
	                String.valueOf(dto.getMobileNo()),
	                user.getRole()
	        );
	        token="Bearer "+token;
	        LoginResponceDTO loginResponceDTO= new LoginResponceDTO(token, user.getRole());

	        ResponseStructure<LoginResponceDTO> rs = new ResponseStructure<>();
	        rs.setStatuscode(200);
	        rs.setMessage("Login successful");
	        rs.setData(loginResponceDTO);

	        return ResponseEntity.ok(rs);
	    }

}
