package com.inventory.repository;

import com.inventory.entity.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchases, Long> {
    // You can add custom query methods here if needed
}

