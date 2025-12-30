package com.aan.LetsRide.Security;


import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.aan.LetsRide.entity.Userr;
import com.aan.LetsRide.repository.Userrepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Userrepo userrRepository;

    public UserDetailsServiceImpl(Userrepo userrRepository) {
        this.userrRepository = userrRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mobno) {

        long mobile;
        try {
            mobile = Long.parseLong(mobno);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Invalid mobile number");
        }

        Userr user = userrRepository.findByMobileno(mobile);
        if(user==null) {
        	throw new UsernameNotFoundException("User not found with mobile: " + mobno);
        }
        		
              
                        

        return User.builder()
                .username(String.valueOf(user.getMobileno()))
                .password(user.getPassword()) // already encoded
                .roles(user.getRole())        // CUSTOMER / DRIVER / ADMIN
                .build();
    }
}