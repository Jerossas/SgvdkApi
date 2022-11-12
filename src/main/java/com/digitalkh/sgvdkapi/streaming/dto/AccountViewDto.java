package com.digitalkh.sgvdkapi.streaming.dto;

import java.util.Collection;

import com.digitalkh.sgvdkapi.streaming.model.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountViewDto {

	private Long id;
	private AccountType accountType;
	private String description; 
	private Long price;
	private String imageUrl;
	private Collection<AccountProfileViewDto> profiles;
}
