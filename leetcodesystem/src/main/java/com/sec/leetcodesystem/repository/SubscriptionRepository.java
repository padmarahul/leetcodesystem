package com.sec.leetcodesystem.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.leetcodesystem.entities.Customer;
import com.sec.leetcodesystem.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByCustomerAndIsActive(Customer customer, boolean isActive);
 // Custom query to find subscription by customer ID
    Optional<Subscription> findByCustomer_CustomerId(int customerId);
}

