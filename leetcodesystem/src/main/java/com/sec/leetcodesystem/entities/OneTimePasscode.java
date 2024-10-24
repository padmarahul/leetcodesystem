package com.sec.leetcodesystem.entities;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="one_time_passcode")
public class OneTimePasscode {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="otp")
	private long otp;
	
	@Column(name = "generated_time")
	private LocalDateTime generatedTime;
	
	@OneToOne
	@MapsId
	private User user;

	public OneTimePasscode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getOtp() {
		return otp;
	}

	public void setOtp(long otp) {
		this.otp = otp;
	}

	public LocalDateTime getGeneratedTime() {
		return generatedTime;
	}

	public void setGeneratedTime(LocalDateTime generatedTime) {
		this.generatedTime = generatedTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OneTimePasscode(int id, long otp, LocalDateTime generatedTime, User user) {
		super();
		this.id = id;
		this.otp = otp;
		this.generatedTime = generatedTime;
		this.user = user;
	}


	@Override
	public String toString() {
		return "OneTimePasscode [id=" + id + ", otp=" + otp + ", generatedTime=" + generatedTime + ", user=" + user
				+ "]";
	}


}
