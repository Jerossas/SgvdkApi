package com.digitalkh.sgvdkapi.order.service;

import java.util.List;
import java.util.Optional;

import com.digitalkh.sgvdkapi.order.model.Order;
import com.digitalkh.sgvdkapi.user.model.User;

public interface OrderService {

	List<Order> findAll();
	Optional<Order> findById(Long id);
	Order save(Order order);
	String generateOrderNumber();
	List<Order> findByUser(User user);
}
