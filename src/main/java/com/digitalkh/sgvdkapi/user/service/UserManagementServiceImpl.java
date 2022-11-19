package com.digitalkh.sgvdkapi.user.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkh.sgvdkapi.user.dto.UserViewDetailsDto;
import com.digitalkh.sgvdkapi.user.model.ERole;
import com.digitalkh.sgvdkapi.user.repository.UserRepository;

@Service
public class UserManagementServiceImpl {

	@Autowired
	private UserRepository userRepository;
	
	public Collection<UserViewDetailsDto> getUsers(){
		return userRepository.findAll().stream()
				.filter(user -> user.getRole().getName().equals(ERole.ROLE_USER))
				.map(user -> new UserViewDetailsDto(user.getName(), user.getLastname(), user.getPhone(), user.getEmail()))
				.toList();
	}
}
