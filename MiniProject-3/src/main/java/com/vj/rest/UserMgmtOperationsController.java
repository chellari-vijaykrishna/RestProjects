package com.vj.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vj.bindings.ActivateUser;
import com.vj.bindings.LoginCredentials;
import com.vj.bindings.RecoverPassword;
import com.vj.bindings.UserAccount;
import com.vj.entity.UserMaster;
import com.vj.service.IUserMgmtService;

@RestController
@RequestMapping("/user-api")
public class UserMgmtOperationsController {

	@Autowired
	private IUserMgmtService service;

	@PostMapping("/save-user")
	public ResponseEntity<String> registerUser(@RequestBody UserAccount user) {
		try {
			String resMsg = service.registerUser(user);
			return new ResponseEntity<String>(resMsg, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/active-user")
	public ResponseEntity<String> ActiveUser(@RequestBody ActivateUser user) {
		try {
			String resMsg = service.AcivateUserAccount(user);
			return new ResponseEntity<String>(resMsg, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login-user")
	public ResponseEntity<String> LoginUser(@RequestBody LoginCredentials cred) {
		try {
			String resMsg = service.login(cred);
			return new ResponseEntity<String>(resMsg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/getpassword-user")
	public ResponseEntity<String> GetPassword(@RequestBody RecoverPassword recover) {
		try {
			String resMsg = service.recoverPassword(recover);
			return new ResponseEntity<String>(resMsg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/changestatus-user")
	public ResponseEntity<String> StatusChange(@RequestParam Integer userId, @RequestParam String changeStatus) {
		try {
			String resMsg = service.changeUserStatus(userId, changeStatus);
			return new ResponseEntity<String>(resMsg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listUser")
	public ResponseEntity<?> ListUsers() {
		try {
			List<UserMaster> resMsg = service.listUsers();
			return new ResponseEntity<List<UserMaster>>(resMsg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete-user")
	public ResponseEntity<String> RemoveUser(Integer userId)
	{
		try {
			String resMsg = service.deleteUser(userId);
			return new ResponseEntity<String>(resMsg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/showUserEmailAndName-user")
	public ResponseEntity<?> ShowUserByEmailAndName(String email, String name)
	{
		try {
			System.out.println(email+name);
			UserAccount resMsg = service.showUserByEmailAndName(email, name);
			return new ResponseEntity<UserAccount>(resMsg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/showUserById-user")
	public ResponseEntity<?> ShowUserById(Integer userId)
	{
		try {
			UserMaster resMsg = service.showUserByUserId(userId);
			return new ResponseEntity<UserMaster>(resMsg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/update-user")
	public ResponseEntity<String> UpdateUser(UserAccount user)
	{
		try {
			String resMsg = service.updateUser(user);
			return new ResponseEntity<String>(resMsg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
