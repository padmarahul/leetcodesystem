package com.sec.leetcodesystem.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name = "username")
	public String username;
	
	
	public String getUsername() {
		return username;
	}
	
	   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Discussion> discussions = new ArrayList<>();

	   // Mapping to Subscriptions (One Customer -> Many Subscriptions)
	    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Subscription> subscriptions = new ArrayList<>();

	    // Mapping to Code Submissions (One Customer -> Many Code Submissions)
	    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<CodeSubmission> codeSubmissions = new ArrayList<>();
	public List<Subscription> getSubscriptions() {
			return subscriptions;
		}

		public void setSubscriptions(List<Subscription> subscriptions) {
			this.subscriptions = subscriptions;
		}

		public List<CodeSubmission> getCodeSubmissions() {
			return codeSubmissions;
		}

		public void setCodeSubmissions(List<CodeSubmission> codeSubmissions) {
			this.codeSubmissions = codeSubmissions;
		}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<Discussion> getDiscussions() {
		return discussions;
	}

	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	

	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	@Column
	@Size(min = 8,message = "Password should contain atleast 8 characters")
	private String password;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "email_id")
	private String emailId;
	
	
	@Column
	private String gender;
	
	@Column(name = "mobileno")
	private long mobileNumber;
	
	@Column(name = "zipcode")
	private long zipcode;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(int customerId, String username,
			@Size(min = 8, message = "Password should contain atleast 8 characters") String password, String fullName,
			String emailId, String gender,long mobileNumber, long zipcode, User user) {
		super();
		this.customerId = customerId;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.emailId = emailId;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.zipcode = zipcode;
		this.user=user;
	}

	public Customer(int customerId, String username,
			@Size(min = 8, message = "Password should contain atleast 8 characters") String password, String fullName,
			String emailId, String gender, long mobileNumber, long zipcode) {
		super();
		this.customerId = customerId;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.emailId = emailId;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", username=" + username + ", password=" + password
				+ ", fullName=" + fullName + ", emailId=" + emailId + ", gender=" + gender + ", mobileNumber="
				+ mobileNumber + ", zipcode=" + zipcode + ", user=" + user + "]";
	}

	

	

	
	
}
