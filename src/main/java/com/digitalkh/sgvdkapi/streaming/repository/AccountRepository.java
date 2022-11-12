package com.digitalkh.sgvdkapi.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkh.sgvdkapi.streaming.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
