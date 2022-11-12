package com.digitalkh.sgvdkapi.streaming.service;

import com.digitalkh.sgvdkapi.streaming.dto.AccountProfileViewDto;
import com.digitalkh.sgvdkapi.streaming.model.AccountProfile;

public interface AccountProfileService {

	AccountProfile create(Long accountId, AccountProfile accountProfile);
	AccountProfile get(Long accountId);
	AccountProfile update(Long accountId, AccountProfile accountProfile);
	Boolean delete(Long profileId);
	AccountProfileViewDto toDto(AccountProfile AccountProfile);
}
