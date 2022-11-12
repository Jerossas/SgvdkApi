package com.digitalkh.sgvdkapi.streaming.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkh.sgvdkapi.streaming.enumeration.EAccountType;
import com.digitalkh.sgvdkapi.streaming.model.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long>{
	Optional<AccountType> findByName(EAccountType name);
}
