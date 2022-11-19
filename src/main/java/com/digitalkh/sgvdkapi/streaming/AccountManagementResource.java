package com.digitalkh.sgvdkapi.streaming;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkh.sgvdkapi.streaming.dto.EditAccountDto;
import com.digitalkh.sgvdkapi.streaming.model.Account;
import com.digitalkh.sgvdkapi.streaming.model.AccountProfile;
import com.digitalkh.sgvdkapi.streaming.model.AccountType;
import com.digitalkh.sgvdkapi.streaming.repository.AccountTypeRepository;
import com.digitalkh.sgvdkapi.streaming.service.AccountProfileService;
import com.digitalkh.sgvdkapi.streaming.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/admin/management/account")
public class AccountManagementResource {

	@Autowired
	private AccountService accountServiceImpl;
	
	@Autowired
	private AccountProfileService accountProfileServiceImpl;
	
	@Autowired
	private AccountTypeRepository accountTypeRepository;
	
	// Accounts
	
	@GetMapping("/list")
	public Collection<Account> getAccounts(){
		return accountServiceImpl.list();
	}
	
	@PostMapping("/create")
	public Account createAccount(@RequestBody Account account){
		return accountServiceImpl.create(account);
	}
	
	@GetMapping("/get/{id}")
	public Account getAccount(@PathVariable("id") Long id){		
		return accountServiceImpl.get(id);
	}
	
	@PutMapping("/update/{id}")
	public Account updateAccount(@PathVariable("id") Long id, @RequestBody EditAccountDto account){
		account.setId(id);		
		return accountServiceImpl.update(account);
	}
	
	@DeleteMapping("/delete/{id}")
	public Boolean deleteAccount(@PathVariable("id") Long id){
		return accountServiceImpl.delete(id);
	}
	
	@GetMapping(path = "/image/{fileName}", produces = IMAGE_JPEG_VALUE)
	public byte[] getStreamingAccountImg(@PathVariable("fileName") String fileName) throws IOException{
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Images/" + fileName));
	}
	
	// Profiles
	
	@GetMapping("/{accountId}/profile/list")
	public Collection<AccountProfile> getAccountProfiles(@PathVariable("accountId") Long accountId){
		return accountServiceImpl.get(accountId).getProfiles();
	}
	
	@GetMapping("/{accountId}/profile/get/{id}")
	public AccountProfile getAccountProfile(@PathVariable("id") Long id){
		return accountProfileServiceImpl.get(id);
	}
	
	@PostMapping("/{accountId}/profile/create")
	public AccountProfile saveAccountProfile(@PathVariable("accountId") Long accountId, @RequestBody AccountProfile accountProfile){
		return accountProfileServiceImpl.create(accountId, accountProfile);
	}
	
	@PutMapping("/{accountId}/profile/update/{profileId}")
	public AccountProfile updateAccountProfile(
			@PathVariable("profileId") Long profileId,
			@PathVariable("accountId") Long accountId,
			@RequestBody AccountProfile accountProfile
			){
		accountProfile.setId(profileId);
		
		return accountProfileServiceImpl.update(accountId, accountProfile);
	}
	
	@DeleteMapping("/{accountId}/profile/delete/{profileId}")
	public Boolean deleteAccountProfile(
			@PathVariable("profileId") Long profileId,
			@PathVariable("accountId") Long accountId){
		
		return accountProfileServiceImpl.delete(profileId);
	}
	
	// Account types
	
	@GetMapping("/type/list")
	public Collection<AccountType> getAllAccountTypes(){
		return accountTypeRepository.findAll();
	}
}
