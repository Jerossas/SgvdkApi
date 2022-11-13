package com.digitalkh.sgvdkapi.streaming.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.digitalkh.sgvdkapi.streaming.enumeration.EAccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "account_types")
@NoArgsConstructor
@EqualsAndHashCode
public class AccountType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private EAccountType name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "accountType", cascade = CascadeType.ALL)
	private Collection<Account> accounts;
	
	public AccountType(EAccountType name) {
		this.name = name;
	}
}
