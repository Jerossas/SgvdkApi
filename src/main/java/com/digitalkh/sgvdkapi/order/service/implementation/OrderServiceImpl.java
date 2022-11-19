package com.digitalkh.sgvdkapi.order.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkh.sgvdkapi.order.model.Order;
import com.digitalkh.sgvdkapi.order.repository.OrderRepository;
import com.digitalkh.sgvdkapi.order.service.OrderService;
import com.digitalkh.sgvdkapi.user.model.User;
import com.digitalkh.sgvdkapi.user.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public List<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}
	
	@Override
	public String delete(Long id) {
		var order = orderRepository.findById(id).orElse(null);
		var user = order.getUser();
		user.getOrders().remove(order);
		
		userRepository.save(user);
		return "The order has been removed!";
	}
}
