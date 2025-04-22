package com.vj.service;

import java.util.List;

import com.vj.bindings.ActivateUser;
import com.vj.bindings.LoginCredentials;
import com.vj.bindings.RecoverPassword;
import com.vj.bindings.UserAccount;
import com.vj.entity.UserMaster;

public interface IUserMgmtService {
	
	public String registerUser(UserAccount user) throws Exception;
	public String AcivateUserAccount(ActivateUser user) throws Exception;
	public String login(LoginCredentials cred) throws Exception;
	
	public UserMaster showUserByUserId(Integer id);
	public List<UserMaster> listUsers();
	public UserAccount showUserByEmailAndName(String email,String name);
	
	public String updateUser(UserAccount user);
	public String deleteUser(Integer userId);
	
	public String changeUserStatus(Integer userId, String changeStatus) throws Exception;
	public String recoverPassword(RecoverPassword recoverPassword) throws Exception;
	
}
