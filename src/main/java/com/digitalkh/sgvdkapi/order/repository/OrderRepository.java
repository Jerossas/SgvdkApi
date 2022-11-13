package com.digitalkh.sgvdkapi.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkh.sgvdkapi.order.model.Order;
import com.digitalkh.sgvdkapi.user.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	List<Order> findByUser(User user);
}
