package com.digitalkh.sgvdkapi.user.authentication.signup.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService implements EmailSender {

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Async
	@Override
	public void send(String to, String email) {
		MimeMessage msg = javaMailSender.createMimeMessage();		
		MimeMessageHelper helper = new MimeMessageHelper(msg, "utf-8");
		
		try {
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("mosqueralozanojhoanferley@gmail.com");
			javaMailSender.send(msg);
			
		} catch (MessagingException e) {
			log.error("Failed to send email!", e);
			throw new IllegalStateException("Failed to send email");
		}
	}
}
