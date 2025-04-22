package com.vj.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.vj.bindings.ActivateUser;
import com.vj.bindings.LoginCredentials;
import com.vj.bindings.RecoverPassword;
import com.vj.bindings.UserAccount;
import com.vj.entity.UserMaster;
import com.vj.repo.IUserMasterRepo;
import com.vj.utils.EmailUtils;

@Service
public class IUserMgmServiceImpl implements IUserMgmtService {

	@Autowired
	private IUserMasterRepo repo;

	@Autowired
	private Environment env;

	@Autowired
	private EmailUtils emailUtils;

	// OTP Generation
	private static final String OTP_CHARACTERS = "0123456789";

	@Override
	public String registerUser(UserAccount user) throws Exception {

		UserMaster userMaster = new UserMaster();
		BeanUtils.copyProperties(user, userMaster);

		String tempPassword = generatePassword(8);
		userMaster.setPassword(tempPassword);
		userMaster.setActive_sw("In-Active");
		UserMaster res = repo.save(userMaster);
		// TODO Mail
		String subject = "User Registration Success";
		String body = readEmailMessageBody(env.getProperty("mailbody.registeruser.location"), user.getName(),
				tempPassword);
		emailUtils.sendEmailMessage(user.getEmail(), subject, body);

		return userMaster != null ? "Registration Successfull with ID: " + res.getUserId()
				: "Problem with Registsrtion";
	}

	private static String generatePassword(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder(length);
		for (int i = 0; i < 12; i++) {
			int randomIndex = random.nextInt(characters.length());
			password.append(characters.charAt(randomIndex));
		}
		return password.toString();
	}

	@Override
	public String AcivateUserAccount(ActivateUser user) throws Exception {

		UserMaster userMaster = repo.findByEmail(user.getEmail());

		System.out.println(user.getEmail());
		System.out.println(userMaster.getEmail());

		if (userMaster.getEmail().equals(user.getEmail())) {
			userMaster.setPassword(user.getConfirmPassword());
			userMaster.setActive_sw("Active");
			repo.save(userMaster);
			// TODO Mail
			String subject = "User Activation Success";
			String body = readEmailMessageBody(env.getProperty("mailbody.activation.location"), userMaster.getName());
			emailUtils.sendEmailMessage(user.getEmail(), subject, body);

			return "User Activated With New Password";
		} else {

			return "User not Found";
		}
	}

	@Override
	public String login(LoginCredentials cred) throws Exception {

		UserMaster userMaster = repo.findByEmailAndPassword(cred.getEmail(), cred.getPassword());

		if (userMaster == (null))
			return "Invaild Credientails";
		else {
			if (userMaster.getActive_sw().equalsIgnoreCase("Active"))
				return "Login Succesful";
			else {
				// TODO Mail
				String subject = "User Not Active";
				String body = readEmailMessageBody(env.getProperty("mailbody.notactivation.location"),
						userMaster.getName());
				emailUtils.sendEmailMessage(userMaster.getEmail(), subject, body);

				return "User Not Active";
			}
		}
	}

	@Override
	public String recoverPassword(RecoverPassword recoverPassword) throws Exception {

		UserMaster userMaster = repo.findByNameAndEmail(recoverPassword.getName(), recoverPassword.getEmail());

		if (userMaster == null)
			return "User Not Found";

		else {
			if (userMaster.getActive_sw().equalsIgnoreCase("In-Active")) {
				return "User found with In-Active Status";
			} else {
				String sentOTP = generateNumericOTP(6);
				// TODO Mail
				String subject = "OTP for Change Active Status : " + sentOTP;
				int numberOTP = Integer.parseInt(sentOTP);
				String body = readEmailMessageBody(env.getProperty("mailbody.userotp.location"),
						recoverPassword.getName(), numberOTP);
				emailUtils.sendEmailMessage(recoverPassword.getEmail(), subject, body);
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter OTP: ");
				int inputOTPInt = sc.nextInt();
				String inputOTP = inputOTPInt + "";
				sc.close();
				System.out.println(inputOTP + "    " + sentOTP);
				boolean flag = otpVerification(inputOTP, sentOTP);
				if (flag == true) {
					String subject1 = "Password Reterived";
					String body1 = readEmailMessageBody(env.getProperty("mailbody.recoverpwd.location"),
							recoverPassword.getName(), userMaster.getPassword());
					emailUtils.sendEmailMessage(recoverPassword.getEmail(), subject1, body1);

					return "Password Sent";
				} else {
					return "Invalid OTP";
				}
			}

		}
	}

	private boolean otpVerification(String returnedOTP, String sentOTP) {
		System.out.println("OTP Checking:.........");
		return returnedOTP.equals(sentOTP) ? true : false;

	}

	private static String generateNumericOTP(int length) {
		SecureRandom random = new SecureRandom();
		StringBuilder otp = new StringBuilder(length);

		// Generate the OTP using the available digits
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(OTP_CHARACTERS.length());
			otp.append(OTP_CHARACTERS.charAt(index));
		}
		return otp.toString();
	}

	@Override
	public String changeUserStatus(Integer userId, String changeStatus) throws Exception {
		Optional<UserMaster> userMaster = repo.findById(userId);

		if (userMaster.isPresent()) {
			String sentOTP = generateNumericOTP(6);
			// TODO Mail
			String subject = "OTP for Change Active Status : " + sentOTP;
			int numberOTP = Integer.parseInt(sentOTP);
			String body = readEmailMessageBody(env.getProperty("mailbody.userotp.location"), userMaster.get().getName(),
					numberOTP);
			emailUtils.sendEmailMessage(userMaster.get().getEmail(), subject, body);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter OTP: ");
			int inputOTPInt = sc.nextInt();
			String inputOTP = inputOTPInt + "";
			sc.close();
			System.out.println(inputOTP + "    " + sentOTP);
			boolean flag = otpVerification(inputOTP, sentOTP);
			if (flag == true) {
				UserMaster user = userMaster.get();
				System.out.println(changeStatus);
				user.setActive_sw(changeStatus);
				repo.save(user);
				String subject1 = "User Status Changed";
				String body1 = readEmailMessageBody(env.getProperty("mailbody.userstatuschange.location"),
						userMaster.get().getName());
				emailUtils.sendEmailMessage(userMaster.get().getEmail(), subject1, body1);

				return "Status Changed";
			} else {
				return "Invalid OTP";
			}
		} else
			return "User not Found";

	}

	@Override
	public List<UserMaster> listUsers() {
		List<UserMaster> list = repo.findAll();
		return list;
	}

	@Override
	public String deleteUser(Integer userId) {
		repo.deleteById(userId);
		return "User Deleted : " + userId;
	}

	@Override
	public UserAccount showUserByEmailAndName(String email, String name) {
		UserMaster userMaster = repo.findByNameAndEmail(name, email);

		UserAccount userAccount = null;
		if (userMaster != null) {
			userAccount = new UserAccount();
			BeanUtils.copyProperties(userMaster, userAccount);
		}

		return userAccount;
	}

	@Override
	public UserMaster showUserByUserId(Integer id) {
		Optional<UserMaster> userMaster = repo.findById(id);

		if (userMaster.isPresent()) {
			return userMaster.get();
		} else
			return null;

	}

	@Override
	public String updateUser(UserAccount user) {
		Optional<UserMaster> master = repo.findById(user.getUserId());
		if (master.isPresent()) {
			UserMaster userMaster = master.get();
			BeanUtils.copyProperties(user, userMaster);
			repo.save(userMaster);
			return "Updated";
		} else
			return "Problem with User details";
	}

	private String readEmailMessageBody(String fileName, String fullName, String pwd) throws Exception {
		String mailBody = null;
		String url = "http://localhost:4041/user-api/active-user";
		try (FileReader reader = new FileReader(fileName); BufferedReader br = new BufferedReader(reader)) {
			StringBuffer buffer = new StringBuffer();
			String line = null;
			do {
				line = br.readLine();
				if (line != null)
					buffer.append(line);
			} while (line != null);

			mailBody = buffer.toString();
			mailBody = mailBody.replace("{FULL_NAME}", fullName);
			mailBody = mailBody.replace("{PWD}", pwd);
			mailBody = mailBody.replace("{URL}", url);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
		return mailBody;
	}

	private String readEmailMessageBody(String fileName, String fullName) throws Exception {
		String mailBody = null;
		String url = "http://localhost:4041/user-api/active-user";
		try (FileReader reader = new FileReader(fileName); BufferedReader br = new BufferedReader(reader)) {
			StringBuffer buffer = new StringBuffer();
			String line = null;
			do {
				line = br.readLine();
				if (line != null)
					buffer.append(line);
			} while (line != null);

			mailBody = buffer.toString();
			mailBody = mailBody.replace("{FULL_NAME}", fullName);
			mailBody = mailBody.replace("{URL}", url);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
		return mailBody;
	}

	private String readEmailMessageBody(String fileName, String fullName, Integer sentOTP) throws Exception {
		String mailBody = null;
		String url = "http://localhost:4041/user-api/active-user";
		String OTP = sentOTP + "";
		try (FileReader reader = new FileReader(fileName); BufferedReader br = new BufferedReader(reader)) {
			StringBuffer buffer = new StringBuffer();
			String line = null;
			do {
				line = br.readLine();
				if (line != null)
					buffer.append(line);
			} while (line != null);

			mailBody = buffer.toString();
			mailBody = mailBody.replace("{FULL_NAME}", fullName);
			mailBody = mailBody.replace("{URL}", url);
			mailBody = mailBody.replace("{OTP}", OTP);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
		return mailBody;
	}

}
