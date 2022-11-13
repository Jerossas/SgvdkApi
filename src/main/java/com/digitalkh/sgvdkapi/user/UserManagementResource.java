package com.digitalkh.sgvdkapi.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkh.sgvdkapi.order.model.Order;
import com.digitalkh.sgvdkapi.user.dto.UserViewDetailsDto;
import com.digitalkh.sgvdkapi.user.service.UserManagementServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/admin/management/")
public class UserManagementResource {

	@Autowired
	private UserManagementServiceImpl serviceImpl;
	
	@GetMapping("/user/list")
	public Collection<UserViewDetailsDto> getUsers(){
		return serviceImpl.getUsers();
	}
	
	@GetMapping("/order/list")
	public Collection<Order> getOrders(){
		return serviceImpl.getOrders();
	}
	
	@GetMapping("/order/{id}")
	public Order getOrder(@PathVariable("id") Long id){
		return serviceImpl.getOrder(id);
	}
}
