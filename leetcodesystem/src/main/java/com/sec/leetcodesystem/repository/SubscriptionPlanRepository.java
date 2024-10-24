package com.sec.leetcodesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.leetcodesystem.entities.Customer;
import com.sec.leetcodesystem.entities.SubscriptionPlan;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {
    SubscriptionPlan findByPlanType(String planType);
    
}
