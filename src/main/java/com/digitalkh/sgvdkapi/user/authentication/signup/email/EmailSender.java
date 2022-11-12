package com.digitalkh.sgvdkapi.user.authentication.signup.email;

public interface EmailSender {

	void send(String to, String email);
}
