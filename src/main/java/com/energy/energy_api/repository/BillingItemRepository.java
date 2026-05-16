package com.energy.energy_api.repository;

import com.energy.energy_api.model.BillingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingItemRepository extends JpaRepository<BillingItem, Long> {
}