package com.digitalkh.sgvdkapi.user.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
@EqualsAndHashCode
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5169666900009171100L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private Collection<User> users;

	public Role(ERole name) {
		this.name = name;
	}
}