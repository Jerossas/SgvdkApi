package com.digitalkh.sgvdkapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePersonalInfoDto {

	private String name;
	private String lastname;
	private String phone;  
	private String email;
}
