package com.energy.energy_api.repository;

import com.energy.energy_api.model.BillingRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRunRepository extends JpaRepository<BillingRun, Long> {
}