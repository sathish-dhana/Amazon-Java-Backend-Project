package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.beans.Product;

public interface CustomerCrudRepo extends JpaRepository<Product, Integer> {

}
