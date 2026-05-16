package com.energy.energy_api.repository;

import com.energy.energy_api.model.EnergyCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyCustomerRepository extends JpaRepository<EnergyCustomer, Long> {
}