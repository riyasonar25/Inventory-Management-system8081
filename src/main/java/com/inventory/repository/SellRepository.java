package com.inventory.repository;

import com.inventory.entity.Sell;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {
    // You can add custom query methods here if needed
}

