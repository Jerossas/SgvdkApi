package com.digitalkh.sgvdkapi.user.authentication.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class SignUpRequest {
	
	private String name;
	private String lastname;
	private String phone;
    private String email;
    private String password;
}
