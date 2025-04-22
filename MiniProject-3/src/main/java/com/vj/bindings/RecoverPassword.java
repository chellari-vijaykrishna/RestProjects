package com.vj.bindings;

public class RecoverPassword {
	private String name;
	private String email;
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public RecoverPassword(String name, String email) {
		this.name = name;
		this.email = email;
	}
	public RecoverPassword() {
		
	}
	
	
	
}
