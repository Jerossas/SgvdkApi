package com.digitalkh.sgvdkapi.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.digitalkh.sgvdkapi.streaming.model.Account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "order_details")
@NoArgsConstructor
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int amount;
	private Long price;
	private Long total;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private Account account;

	public OrderDetail(Long id, String name, int amount, Long price, Long total) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.total = total;
	}
}
