package com.digitalkh.sgvdkapi.order.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.digitalkh.sgvdkapi.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6372297526643214404L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime creationDate;
	private Long total;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails;

	public Order(LocalDateTime creationDate, Long total, User user) {
		this.creationDate = creationDate;
		this.total = total;
		this.user = user;
	}

	public Order(LocalDateTime creationDate, Long total, User user, List<OrderDetail> orderDetails) {
		super();
		this.creationDate = creationDate;
		this.total = total;
		this.user = user;
		this.orderDetails = orderDetails;
	}
}
