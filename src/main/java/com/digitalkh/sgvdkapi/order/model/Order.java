package com.digitalkh.sgvdkapi.order.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.digitalkh.sgvdkapi.user.model.User;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String number;
	private LocalDateTime creationDate;
	private Long total;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private List<OrderDetail> orderDetails;

	public Order(String number, LocalDateTime creationDate, Long total, User user) {
		this.number = number;
		this.creationDate = creationDate;
		this.total = total;
		this.user = user;
	}
}
