package com.digitalkh.sgvdkapi.streaming;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkh.sgvdkapi.streaming.dto.AccountProfileViewDto;
import com.digitalkh.sgvdkapi.streaming.dto.AccountViewDto;
import com.digitalkh.sgvdkapi.streaming.model.AccountType;
import com.digitalkh.sgvdkapi.streaming.repository.AccountTypeRepository;
import com.digitalkh.sgvdkapi.streaming.service.implementation.AccountProfileServiceImpl;
import com.digitalkh.sgvdkapi.streaming.service.implementation.AccountServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/public/account")
@RequiredArgsConstructor
public class AccountResource {

	@Autowired
	private AccountServiceImpl accountServiceImpl;

	@Autowired
	private AccountProfileServiceImpl accountProfileServiceImpl;

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	// Accounts

	@GetMapping("/list")
	public Collection<AccountViewDto> getAccounts() {
		return accountServiceImpl.list().stream().map(account -> accountServiceImpl.toDto(account))
				.collect(Collectors.toList());
	}

	@GetMapping("/get/{id}")
	public AccountViewDto getAccount(@PathVariable("id") Long id) {
		return accountServiceImpl.toDto(accountServiceImpl.get(id));
	}

	@GetMapping(path = "/image/{fileName}", produces = IMAGE_JPEG_VALUE)
	public byte[] getStreamingAccountImg(@PathVariable("fileName") String fileName) throws IOException {
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Images/" + fileName));
	}

	// Profiles

	@GetMapping("/{accountId}/profile/list")
	public Collection<AccountProfileViewDto> getAccountProfiles(@PathVariable("accountId") Long accountId) {
		return accountServiceImpl.get(accountId).getProfiles().stream()
				.map(accountProfile -> accountProfileServiceImpl.toDto(accountProfile)).collect(Collectors.toList());
	}

	@GetMapping("/{accountId}/profile/get/{id}")
	public AccountProfileViewDto getAccountProfile(@PathVariable("id") Long id) {
		return accountProfileServiceImpl.toDto(accountProfileServiceImpl.get(id));
	}

	// Account types

	@GetMapping("/type/list")
	public Collection<AccountType> getAllAccountTypes() {
		return accountTypeRepository.findAll();
	}
}
