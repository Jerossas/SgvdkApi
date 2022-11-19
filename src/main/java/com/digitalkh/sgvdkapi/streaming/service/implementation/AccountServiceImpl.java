package com.digitalkh.sgvdkapi.streaming.service.implementation;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalkh.sgvdkapi.streaming.dto.AccountProfileViewDto;
import com.digitalkh.sgvdkapi.streaming.dto.AccountViewDto;
import com.digitalkh.sgvdkapi.streaming.model.Account;
import com.digitalkh.sgvdkapi.streaming.model.AccountProfile;
import com.digitalkh.sgvdkapi.streaming.repository.AccountRepository;
import com.digitalkh.sgvdkapi.streaming.service.AccountService;
import com.digitalkh.sgvdkapi.streaming.dto.EditAccountDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public Account create(Account account) {
		log.info("Saving new Streaming account: {}", account.getEmail());
		return accountRepository.save(account);
	}

	@Override
	public Collection<Account> list() {
		log.info("Fetching all Streaming accounts");
		return accountRepository.findAll();
	}

	@Override
	public Account get(Long accountId) {
		log.info("Fetching Streaming account by id: {}", accountId);
		return accountRepository.findById(accountId).orElse(null);
	}

	@Override
	public Account update(EditAccountDto account) {
		log.info("Updating Streaming account: {}", account.getEmail());
		Account updateAccount = accountRepository.findById(account.getId()).orElse(null);
		
		updateAccount.setEmail(account.getEmail());
		updateAccount.setPassword(account.getPassword());
		updateAccount.setAccountType(account.getAccountType());
		updateAccount.setDescription(account.getDescription());
		updateAccount.setPrice(account.getPrice());
		updateAccount.setImageUrl(account.getImageUrl());
		
		return accountRepository.save(updateAccount);
	}

	@Override
	public Boolean delete(Long accountId) {
		log.info("Deleting Streaming account by id: {}", accountId);
		
		if(!accountRepository.existsById(accountId)) {
			log.error("Streaming account with id {}, does not exist.", accountId);
			return FALSE;
		}
		
		accountRepository.deleteById(accountId);
		return TRUE;
	}

	@Override
	public AccountViewDto toDto(Account account) {
		
		Collection<AccountProfileViewDto> profilesDto = account.getProfiles()
				.stream().map(accountProfile -> profileToDto(accountProfile))
				.collect(Collectors.toSet());
		
		AccountViewDto dto = new AccountViewDto();
		dto.setId(account.getId());
		dto.setDescription(account.getDescription());
		dto.setAccountType(account.getAccountType());
		dto.setPrice(account.getPrice());
		dto.setImageUrl(account.getImageUrl());
		dto.setProfiles(profilesDto);
		
		return dto;
	}
	
	private AccountProfileViewDto profileToDto(AccountProfile accountProfile) {
		AccountProfileViewDto dto = new AccountProfileViewDto();
		dto.setId(accountProfile.getId());
		dto.setName(accountProfile.getName());
		
		return dto;
	}
}
