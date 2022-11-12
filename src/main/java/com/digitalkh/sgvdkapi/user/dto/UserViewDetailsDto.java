package com.digitalkh.sgvdkapi.user.dto;

import java.util.Collection;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserViewDetailsDto {

	private String name;
	private String lastname;
	private String phone;
	private String email;
	private Collection<String> roles;
	
	public UserViewDetailsDto(String name, String lastname, String phone, String email) {
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
	}
	
}
