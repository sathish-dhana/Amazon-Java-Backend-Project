package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Ordered;

@Repository
public interface OrderCrudRepo extends JpaRepository<Ordered, Integer>{

}
