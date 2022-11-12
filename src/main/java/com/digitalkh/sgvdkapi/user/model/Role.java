package com.digitalkh.sgvdkapi.user.model;

import java.util.Collection;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	
	@OneToMany(mappedBy = "role")
	private Collection<User> users;

	public Role(ERole name) {
		this.name = name;
	}
}