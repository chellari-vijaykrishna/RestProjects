package com.vj.bindings;

public class ActivateUser {
	
	private String email;
	private String tempPassword;
	private String newPassword;
	private String confirmPassword;
	
	public String getEmail() {
		return email;
	}
	public String getTempPassword() {
		return tempPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public ActivateUser() {
		
	}
	public ActivateUser(String email, String tempPassword, String newPassword, String confirmPassword) {
		this.email = email;
		this.tempPassword = tempPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}
	
	
	
	
}
