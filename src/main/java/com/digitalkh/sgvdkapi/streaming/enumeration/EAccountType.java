package com.digitalkh.sgvdkapi.streaming.enumeration;

public enum EAccountType {

	NETFLIX("NETFLIX"),
	DISNEY("DISNEY");
	
	private String accountType;
	
	private EAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getAccountType() {
		return accountType;
	}
}
