package com.digitalkh.sgvdkapi.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitalkh.sgvdkapi.order.model.Order;
import com.digitalkh.sgvdkapi.order.service.OrderService;
import com.digitalkh.sgvdkapi.user.authentication.signup.confirmation_token.ConfirmationToken;
import com.digitalkh.sgvdkapi.user.authentication.signup.confirmation_token.ConfirmationTokenService;
import com.digitalkh.sgvdkapi.user.dto.UserChangePasswordDto;
import com.digitalkh.sgvdkapi.user.dto.UserChangePersonalInfoDto;
import com.digitalkh.sgvdkapi.user.dto.UserViewDetailsDto;
import com.digitalkh.sgvdkapi.user.model.User;
import com.digitalkh.sgvdkapi.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfirmationTokenService tokenService;
	
	@Autowired
	private OrderService orderService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

		return user;
	}

	public String signUp(User user) {
		boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

		if (userExists) {
			throw new IllegalStateException("Email already taken!");
		}

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);

		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusHours(1), user);
		
		tokenService.saveConfirmationToken(confirmationToken);
		
		return token;
	}
	
	public int enableUser(String email) {
		return userRepository.enableUser(email);
	}
	
	public String changePassword(UserChangePasswordDto changePasswordDto) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user != null) {
			if (!new BCryptPasswordEncoder().matches(changePasswordDto.getCurrentPassword(), user.getPassword())) {
				throw new IllegalStateException("Current password invalid! try again");
			} else if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmNewPassword())) {
				log.info("newPassword: {}", changePasswordDto.getNewPassword());
				log.info("confirmNewPassword: {}", changePasswordDto.getConfirmNewPassword());
				throw new IllegalStateException("The new password and confirm password dont match!");
			}
		} else {
			return "Any user logged in o user does not exist!";
		}
		
		user.setPassword(new BCryptPasswordEncoder().encode(changePasswordDto.getNewPassword()));
		userRepository.save(user);
		
		return "Your password has been updated";
	}
	
	public String changePersonalInfo(UserChangePersonalInfoDto request) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user != null) {
			user.setName(request.getName());
			user.setLastname(request.getLastname());
			user.setPhone(request.getPhone());
		} else {
			return "Any user logged in o user does not exist!";
		}
		
		userRepository.save(user);		
		return "Your personal information has been updated!";
	}
	
	public ResponseEntity<?> viewProfile() {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user != null) {
			return ResponseEntity.ok(new UserViewDetailsDto(user.getName(), user.getLastname(), user.getPhone(), user.getEmail()));
		}
		
		return ResponseEntity.badRequest().body("Any user logged in o user does not exist!");
	}
	
	public List<Order> getOrders(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return orderService.findByUser(user);
	}
	
	public Order getOrder(Long orderId) {
		return orderService.findById(orderId).orElseThrow(null);
	}
}
