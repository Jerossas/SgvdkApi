package com.digitalkh.sgvdkapi.user.authentication.signin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRequest {
	
	private String email;
	private String password;
}
