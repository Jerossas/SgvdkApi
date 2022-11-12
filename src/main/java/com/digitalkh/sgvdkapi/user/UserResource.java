package com.digitalkh.sgvdkapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkh.sgvdkapi.user.dto.UserChangePasswordDto;
import com.digitalkh.sgvdkapi.user.dto.UserChangePersonalInfoDto;
import com.digitalkh.sgvdkapi.user.service.UserService;

@RestController
@RequestMapping("api/user")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/change-password")
	public String changePassword(@RequestBody UserChangePasswordDto changePasswordDto) {
		return userService.changePassword(changePasswordDto);
	}
	
	@PutMapping("/change-info")
	public String changeInfo(@RequestBody UserChangePersonalInfoDto request) {
		return userService.changePersonalInfo(request);
	}
	
	@GetMapping("profile")
	public ResponseEntity<?> viewProfile(){
		return userService.viewProfile();
	}
}
