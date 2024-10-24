package com.sec.leetcodesystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sec.leetcodesystem.entities.OneTimePasscode;



@Repository
public interface OneTimePasscodeRepository extends JpaRepository<OneTimePasscode, Integer> {

}