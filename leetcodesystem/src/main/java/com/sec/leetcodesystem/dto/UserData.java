package com.sec.leetcodesystem.dto;



import java.time.LocalDate;

public class UserData {

	
public int userId;
public String userName;
public String password;
public String emailId;
private long mobileNumber;
private long zipcode;
public String typeOfUser;
public String firstName;
public String lastName;
public String message;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public long getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(long mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public long getZipcode() {
	return zipcode;
}
public void setZipcode(long zipcode) {
	this.zipcode = zipcode;
}

public String getTypeOfUser() {
	return typeOfUser;
}
public void setTypeOfUser(String typeOfUser) {
	this.typeOfUser = typeOfUser;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}
