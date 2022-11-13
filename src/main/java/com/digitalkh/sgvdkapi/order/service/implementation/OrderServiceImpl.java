package com.digitalkh.sgvdkapi.order.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkh.sgvdkapi.order.model.Order;
import com.digitalkh.sgvdkapi.order.repository.OrderRepository;
import com.digitalkh.sgvdkapi.order.service.OrderService;
import com.digitalkh.sgvdkapi.user.model.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
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
	public String generateOrderNumber() {
		Long number = 0L;
		String concatenatedNumber = "";
		
		List<Order> orders = findAll();
		List<Long> numbers = new ArrayList<>();
		
		orders.stream().forEach(o -> numbers.add(Long.parseLong(o.getNumber())));
		
		if(orders.isEmpty()) {
			number = 1L;
		} else {
			number = numbers.stream().max(Long::compare).get();
			number++;
		}
		
		if(number < 10)
			concatenatedNumber = "000000000" + String.valueOf(number);
		else if(number < 100)
			concatenatedNumber = "00000000" + String.valueOf(number);
		else if(number < 1000)
			concatenatedNumber = "0000000" + String.valueOf(number);
		else if(number < 10000)
			concatenatedNumber = "0000000" + String.valueOf(number);
		
		return concatenatedNumber;
	}

	@Override
	public List<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}

}
