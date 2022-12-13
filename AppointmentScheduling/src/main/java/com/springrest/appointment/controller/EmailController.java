package com.springrest.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	
	// new
////	@Autowired
////	private EmailRepository emailRepository;
//	@Autowired 
//	private JavaMailSender javaMailSender;
//	 
//    @Value("${spring.mail.username}") 
//    private String sender;
//	 
//	@PostMapping("/send")
//	public String sendEmail(@RequestBody EmailDetails emailDetails) {
//		// Creating a simple mail message
////		try {
////			SimpleMailMessage mailMessage = new SimpleMailMessage();
////			
////			// Setting up necessary details
////			mailMessage.setFrom(sender);
////			mailMessage.setTo(emailDetails.getRecipient());
////			mailMessage.setText(emailDetails.getMsgBody());
////			mailMessage.setSubject(emailDetails.getSubject());
////			
////			emailRepository.save(emailDetails);
////			// Sending the mail
////			javaMailSender.send(mailMessage);
////			return "Mail Sent Successfully...";
////		}
//		// Catch block to handle the exceptions
//        catch (Exception e) {
//            return "Error while Sending Mail";
//        }
//	}
    
}
