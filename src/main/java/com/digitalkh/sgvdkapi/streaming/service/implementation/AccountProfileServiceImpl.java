package com.digitalkh.sgvdkapi.streaming.service.implementation;

import static java.lang.Boolean.TRUE;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalkh.sgvdkapi.streaming.dto.AccountProfileViewDto;
import com.digitalkh.sgvdkapi.streaming.model.AccountProfile;
import com.digitalkh.sgvdkapi.streaming.repository.AccountProfileRepository;
import com.digitalkh.sgvdkapi.streaming.service.AccountProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountProfileServiceImpl implements AccountProfileService {

	private final AccountProfileRepository accountProfileRepository;
	private final AccountServiceImpl accountService;
	
	@Override
	public AccountProfile create(Long accountId, AccountProfile accountProfile){
		log.info("Saving new account profile: {}", accountProfile.getName());
		accountProfile.setAccount(accountService.get(accountId));		
		return accountProfileRepository.save(accountProfile);
	}
	
	@Override
	public AccountProfile get(Long profileId) {
		log.info("Fetching account profile by id: {}", profileId);
		return accountProfileRepository.findById(profileId).get();
	}
	
	@Override
	public Boolean delete(Long profileId) {
		log.info("Deleting account profile by id: {}", profileId);
		accountProfileRepository.deleteById(profileId);
		return TRUE;
	}
	
	@Override
	public AccountProfile update(Long accountId, AccountProfile accountProfile) {		
		log.info("Updating account profile: {}", accountProfile.getName());
		accountProfile.setAccount(accountService.get(accountId));	
		return accountProfileRepository.save(accountProfile);
	}
	
	@Override
	public AccountProfileViewDto toDto(AccountProfile accountProfile) {
		AccountProfileViewDto dto = new AccountProfileViewDto();
		dto.setId(accountProfile.getId());
		dto.setName(accountProfile.getName());
		
		return dto;
	}
}
