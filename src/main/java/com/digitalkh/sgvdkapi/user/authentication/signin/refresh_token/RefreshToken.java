package com.digitalkh.sgvdkapi.user.authentication.signin.refresh_token;

import java.time.Instant;

import javax.persistence.*;

import com.digitalkh.sgvdkapi.user.model.User;

import lombok.Data;

@Data
@Entity(name = "refresh_token")
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(nullable = false, unique = true)
	private String token;

	@Column(nullable = false)
	private Instant expiryDate;
}
