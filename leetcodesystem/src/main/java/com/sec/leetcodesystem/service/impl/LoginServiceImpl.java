package com.sec.leetcodesystem.service.impl;


import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.leetcodesystem.dto.UserData;
import com.sec.leetcodesystem.entities.Customer;
import com.sec.leetcodesystem.entities.OneTimePasscode;
import com.sec.leetcodesystem.entities.User;
import com.sec.leetcodesystem.exceptions.InvalidLoginCredentialsException;
import com.sec.leetcodesystem.exceptions.OtpInvalidException;
import com.sec.leetcodesystem.exceptions.UserEmailIdAlreadyExistsException;
import com.sec.leetcodesystem.exceptions.UserNameAlreadyExistsException;
import com.sec.leetcodesystem.exceptions.UserNotFoundException;
import com.sec.leetcodesystem.repository.CustomerRepository;
import com.sec.leetcodesystem.repository.LoginRepository;
import com.sec.leetcodesystem.repository.OneTimePasscodeRepository;
import com.sec.leetcodesystem.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	

	
	@Autowired
	private OneTimePasscodeRepository otpRepository;
	
	
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	
	@Override
	public String signup(User user) throws UserNameAlreadyExistsException, UserEmailIdAlreadyExistsException {
		try {
			if(loginRepository.existsByUserName(user.getUserName())) {
				return "Username already exists";
			}else if(loginRepository.existsByEmailId(user.getEmailId())) {
				return "Email ID already exists";
			}else if(user.getTypeOfUser().equalsIgnoreCase("customer")){
				user.setTypeOfUser("customer");
				loginRepository.save(user);
				otpRepository.save(new OneTimePasscode(user.getUserId(),0,LocalDateTime.now(),user));
				Customer customer = new Customer(user.getUserId(),user.getUserName(),user.getPassword(),user.getFirstName()+" "+user.getLastName(),
							user.getEmailId(),user.getGender(),user.getMobileNumber(),user.getZipcode(),user);
				customerRepository.save(customer);
				return "User added successfully";
			}
			else {
				return "not a valid user";
			}
			
		}catch(Exception e) {
			throw new UserNameAlreadyExistsException(e.getMessage());
		}
	}
		
	@Override
	public UserData loginUserFirstStep(String username, String password)
			throws InvalidLoginCredentialsException, UserNotFoundException {
		try {
			var user = loginRepository.findByUserName(username);
			if(user.isPresent()) {
				if(user.get().getPassword().equals(password)) {
					generateAndSaveOtp(user.get().getUserId());
			User userObj=user.get();
			UserData data= new UserData();
			data.setUserId(userObj.getUserId());
			data.setEmailId(userObj.getEmailId());
			data.setFirstName(userObj.getFirstName());
			data.setLastName(userObj.getLastName());
			data.setMobileNumber(userObj.getMobileNumber());
			data.setPassword(userObj.getPassword());
			data.setTypeOfUser(userObj.getTypeOfUser());
			data.setZipcode(userObj.getZipcode());
			data.setUserId(userObj.getUserId());
			data.setMessage("Enter OTP generated");
			data.setUserName(userObj.getUserName());
			return data;
				}else {
					throw new InvalidLoginCredentialsException("Wrong password");
				}
			}else {
				throw new UserNotFoundException("User doesn't exist");
			}
		}catch(InvalidLoginCredentialsException e) {
			throw new InvalidLoginCredentialsException(e.getMessage());
		}catch(UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		}
	}
	
	@Override
	public String loginUserSecondStep(int id, long otp) throws OtpInvalidException, UserNotFoundException {
		try {
			var user = otpRepository.findById(id);
			if(user.isPresent()) {
				var temp = user.get();
				if(temp.getGeneratedTime().isBefore(temp.getGeneratedTime().plusMinutes(5))) {
					if(temp.getOtp()==otp) {
						var role = getUserById(id);
						return role.getTypeOfUser()+" Login successful";
					}else {
						throw new OtpInvalidException("Invalid OTP");
					}
				}else {
					throw new OtpInvalidException("OTP expired");
				}
			}else {
				throw new UserNotFoundException("User doesn't exist");
			}
		}catch(OtpInvalidException e) {
			throw new OtpInvalidException(e.getMessage());
		}catch(UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		}
	}

	@Override
	public String changePassword(String username, String newPassword) throws UserNotFoundException {
		try {
			var user = loginRepository.findByUserName(username);
			if(user.isPresent()) {
				user.get().setPassword(newPassword);
				loginRepository.save(user.get());
				var role = user.get().getTypeOfUser();
				if(role.toLowerCase().equals("customer")) {
					var customer = customerRepository.findById(user.get().getUserId()).get();
					customer.setPassword(newPassword);
					customerRepository.save(customer);
				}
				return "Password changed successfully";
			}else {
				throw new UserNotFoundException("User doesn't exist");
			}
		}catch(UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		}
	}

	@Override
	public User getUserById(int id) throws UserNotFoundException {
		return loginRepository.findById(id).orElseThrow(()->new UserNotFoundException("User doesn't exist"));
	}

	@Override
	public User getUserByUsername(String username) throws UserNameAlreadyExistsException {
		return loginRepository.findByUserName(username).orElseThrow(()->new UserNameAlreadyExistsException("Username already exists"));
	}

	@Override
	public OneTimePasscode generateAndSaveOtp(int id) throws UserNotFoundException {
		try {
			if(getUserById(id)!=null) {
				Random random = new Random();
				int otp = 1000 + random.nextInt(9000);
		        LocalDateTime time = LocalDateTime.now();
		        OneTimePasscode temp = otpRepository.findById(id).get();
		        temp.setGeneratedTime(time);
		        temp.setOtp(otp);
	        	otpRepository.save(temp);
	        	sendOtpEmail(getUserById(id).getEmailId(), otp, id);
	        	return temp;
	        }else {
	        	throw new UserNotFoundException("User doesn't exist");
	        }
		}catch(UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		} 
	}

	@Override
	public long getOtpById(int id) throws UserNotFoundException {
		try {
			if(getUserById(id)!=null) {
				long otp = otpRepository.findById(id).get().getOtp();
				return otp;
			}else {
				throw new UserNotFoundException("User doesn't exist");
			}
		}catch(UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		}
	}

	@Override
	public User getUserByEmailId(String emailId) throws UserEmailIdAlreadyExistsException {
		return loginRepository.findByEmailId(emailId).orElseThrow(()->new UserEmailIdAlreadyExistsException("User Email ID already exists"));
	}
	
	@Override
	public void sendOtpEmail(String recipientEmail, long otp, int userId) {
        // Create email message
        SimpleMailMessage message = new SimpleMailMessage();
//      message.setFrom("mufasasimba080@gmail.com");
        message.setTo(recipientEmail);
        message.setSubject("Your OTP Verification Code");
        message.setText("Your OTP is: " + otp + " and your user ID is: " + userId);
        // Send the email
        javaMailSender.send(message);
    }

}
