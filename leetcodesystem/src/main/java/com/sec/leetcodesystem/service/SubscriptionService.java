package com.sec.leetcodesystem.service;



import com.sec.leetcodesystem.entities.Customer;
import com.sec.leetcodesystem.entities.Subscription;

public interface SubscriptionService {

	public Subscription createSubscription(Customer customer, String planType) ;
	public void activateSubscription(Customer customer, String planType);
}
