package com.digitalkh.sgvdkapi.streaming.service;

import java.util.Collection;

import com.digitalkh.sgvdkapi.streaming.dto.AccountViewDto;
import com.digitalkh.sgvdkapi.streaming.model.Account;

public interface AccountService {

	Account create(Account account);
	Collection<Account> list();
	Account get(Long accountId);
	Account update(Account account);
	Boolean delete(Long accountId);
	AccountViewDto toDto(Account account);
}
