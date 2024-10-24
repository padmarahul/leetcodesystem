package com.sec.leetcodesystem.service;

import com.sec.leetcodesystem.dto.UserData;
import com.sec.leetcodesystem.entities.OneTimePasscode;
import com.sec.leetcodesystem.entities.User;
import com.sec.leetcodesystem.exceptions.InvalidLoginCredentialsException;
import com.sec.leetcodesystem.exceptions.OtpInvalidException;
import com.sec.leetcodesystem.exceptions.UserEmailIdAlreadyExistsException;
import com.sec.leetcodesystem.exceptions.UserNameAlreadyExistsException;
import com.sec.leetcodesystem.exceptions.UserNotFoundException;

public interface LoginService {

	public String signup(User user) throws UserNameAlreadyExistsException, UserEmailIdAlreadyExistsException;
	
	public UserData loginUserFirstStep(String username, String password) throws InvalidLoginCredentialsException,UserNotFoundException;
	
	public String loginUserSecondStep(int id, long otp) throws OtpInvalidException, UserNotFoundException;
	
	public String changePassword(String username, String newPassword) throws UserNotFoundException;
	
	public User getUserById(int id) throws UserNotFoundException;
	
	public User getUserByUsername(String username) throws UserNameAlreadyExistsException ;
	
	public User getUserByEmailId(String emailId) throws UserEmailIdAlreadyExistsException;
	
	public OneTimePasscode generateAndSaveOtp(int id) throws UserNotFoundException;
	
	public long getOtpById(int id) throws UserNotFoundException;
	
	public void sendOtpEmail(String recipientEmail, long otp, int userId);
	
}
