package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Order;

@Repository
public interface OrderCrudRepo extends JpaRepository<Order, Integer>{

}
