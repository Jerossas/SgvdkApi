package com.digitalkh.sgvdkapi.user.authentication.signup.confirmation_token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {

	@Autowired
	private ConfirmationTokenRepository tokenRepository;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		tokenRepository.save(token);
	}
	
	public Optional<ConfirmationToken> getToken(String token){
		return tokenRepository.findByToken(token);
	}
	
	public int setConfirmedAt(String token) {
		return tokenRepository.updateConfirmedAt(token, LocalDateTime.now());
	}
}
