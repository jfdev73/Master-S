package com.codmind.orderapi.repositories;

import org.springframework.stereotype.Repository;

import com.codmind.orderapi.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
