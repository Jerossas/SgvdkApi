package com.digitalkh.sgvdkapi.streaming.dto;

import com.digitalkh.sgvdkapi.streaming.model.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditAccountDto {

	private Long id;
	private String email;
	private String password;
	private AccountType accountType;
	private String description;
	private Long price;
	private String imageUrl;
	
}
