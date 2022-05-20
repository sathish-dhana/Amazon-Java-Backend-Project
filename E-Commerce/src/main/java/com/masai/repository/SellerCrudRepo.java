package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Seller;

@Repository
public interface SellerCrudRepo extends JpaRepository<Seller, Integer> {

}
