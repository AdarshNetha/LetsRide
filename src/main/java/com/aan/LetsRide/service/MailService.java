package com.aan.LetsRide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;
	public void sendMail(String tomail, String subject, String message) {
		SimpleMailMessage mailMessage= new SimpleMailMessage();
		mailMessage.setTo(tomail);
		mailMessage.setSubject(subject);
		mailMessage.setFrom("abhiadarshanetha849@gmail.com");
		mailMessage.setText(message);
		
		javaMailSender.send(mailMessage);
	}
        
}
