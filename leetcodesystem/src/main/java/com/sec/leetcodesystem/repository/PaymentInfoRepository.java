package com.sec.leetcodesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sec.leetcodesystem.entities.PaymentInfo;



public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, String> {
	
}

