package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Product;

@Repository
public interface ProductCrudRepo extends JpaRepository<Product, Integer>{

}
