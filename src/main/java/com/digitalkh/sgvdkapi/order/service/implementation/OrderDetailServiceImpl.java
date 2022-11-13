package com.digitalkh.sgvdkapi.order.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkh.sgvdkapi.order.model.OrderDetail;
import com.digitalkh.sgvdkapi.order.repository.OrderDetailRepository;
import com.digitalkh.sgvdkapi.order.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository detailRepository;

	@Override
	public OrderDetail save(OrderDetail orderDetail) {
		return detailRepository.save(orderDetail);
	}
}
