package com.digitalkh.sgvdkapi.streaming.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkh.sgvdkapi.streaming.model.AccountProfile;

@Repository
public interface AccountProfileRepository extends JpaRepository<AccountProfile, Long>{

	Optional<AccountProfile> findByName(String name);
}
