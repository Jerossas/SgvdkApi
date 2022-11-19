package com.digitalkh.sgvdkapi.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkh.sgvdkapi.order.model.Order;
import com.digitalkh.sgvdkapi.order.model.OrderDetail;
import com.digitalkh.sgvdkapi.order.service.OrderDetailService;
import com.digitalkh.sgvdkapi.order.service.OrderService;
import com.digitalkh.sgvdkapi.user.model.User;
import com.digitalkh.sgvdkapi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/admin/management/orders")
public class OrderManagementResource {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/list")
	public List<Order> getOrders(){
		return orderService.findAll();
	}
	
	@PostMapping
	public Order saveOrder(@RequestBody Order order) {
		List<OrderDetail> orderDetails = order.getOrderDetails();
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		order.setUser(userRepository.findByEmail(user.getEmail()).get());	
		order = orderService.save(order);
		
		for(OrderDetail od: orderDetails) {
			od.setOrder(order);
//			od.setAccount(accountRepository.findByEmail(od.getAccount().getEmail()).get());
			orderDetailService.save(od);
		}
		return orderService.save(order);
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteOrder(@PathVariable("id") Long id) {
		return orderService.delete(id);
	}
}
