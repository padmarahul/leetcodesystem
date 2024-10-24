package com.sec.leetcodesystem.service;

import com.paypal.api.payments.Payment;
import com.sec.leetcodesystem.entities.Customer;
import com.sec.leetcodesystem.entities.PaymentInfo;

public interface PaymentService {
	
	 public double getPlanRate(String planType);
	 public void savePaymentInfo(Payment payment, String planType, Customer customer);
	  public String checkSubscription(int customerId) throws Exception;
}
