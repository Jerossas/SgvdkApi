package com.digitalkh.sgvdkapi.order.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.digitalkh.sgvdkapi.streaming.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7897629495954401652L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int quantity;
	private Long price;
	private Long subTotal;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public OrderDetail(Long id, String name, int quantity, Long price, Long subTotal) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.subTotal = subTotal;
	}
}
