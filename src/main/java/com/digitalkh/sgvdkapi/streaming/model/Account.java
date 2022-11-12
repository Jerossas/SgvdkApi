package com.digitalkh.sgvdkapi.streaming.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "account_type_id", referencedColumnName = "id")
	private AccountType accountType;
	
	private String description;
	private Long price;	
	private String imageUrl;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AccountProfile> profiles;

	public Account(String email, String password, AccountType accountType, String description, Long price,
			String imageUrl, List<AccountProfile> profiles) {
		super();
		this.email = email;
		this.password = password;
		this.accountType = accountType;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
		this.profiles = profiles;
	}
}
