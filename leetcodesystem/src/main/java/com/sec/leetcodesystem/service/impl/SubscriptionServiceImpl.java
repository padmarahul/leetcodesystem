package com.sec.leetcodesystem.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.leetcodesystem.entities.Customer;
import com.sec.leetcodesystem.entities.Subscription;
import com.sec.leetcodesystem.entities.SubscriptionPlan;
import com.sec.leetcodesystem.repository.SubscriptionPlanRepository;
import com.sec.leetcodesystem.repository.SubscriptionRepository;
import com.sec.leetcodesystem.service.SubscriptionService;

import java.time.LocalDateTime;

@Transactional
@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    public Subscription createSubscription(Customer customer, String planType) {
        SubscriptionPlan plan = subscriptionPlanRepository.findByPlanType(planType);
        if (plan == null) {
            throw new IllegalArgumentException("Invalid subscription plan type.");
        }

        Subscription subscription = new Subscription();
        subscription.setCustomer(customer);
        subscription.setActive(true);
        subscription.setStartDate(LocalDateTime.now());

        if (planType.equalsIgnoreCase("monthly")) {
            subscription.setEndDate(LocalDateTime.now().plusMonths(1));  // Monthly subscription
        } else if (planType.equalsIgnoreCase("yearly")) {
            subscription.setEndDate(LocalDateTime.now().plusYears(1));  // Yearly subscription
        }

        return subscriptionRepository.save(subscription);
    }

    public void activateSubscription(Customer customer, String planType) {
        Subscription existingSubscription = subscriptionRepository.findByCustomerAndIsActive(customer, true);

        if (existingSubscription != null) {
            if (planType.equalsIgnoreCase("monthly")) {
                existingSubscription.setEndDate(existingSubscription.getEndDate().plusMonths(1));
            } else if (planType.equalsIgnoreCase("yearly")) {
                existingSubscription.setEndDate(existingSubscription.getEndDate().plusYears(1));
            }
            subscriptionRepository.save(existingSubscription);
        } else {
            createSubscription(customer, planType);
        }
    }
}
