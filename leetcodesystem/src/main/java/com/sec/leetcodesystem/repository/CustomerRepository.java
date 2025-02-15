package com.sec.leetcodesystem.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sec.leetcodesystem.entities.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("SELECT c FROM Customer c WHERE c.username = :username")
	Customer getCustomerByUsername(String username);

	
}
