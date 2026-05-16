package com.energy.energy_api.controller;

import com.energy.energy_api.model.EnergyCustomer;
import com.energy.energy_api.repository.EnergyCustomerRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = {
    "http://localhost:4200",
    "https://angular.amjadalahdal.com"
})
public class CustomerController {

    private final EnergyCustomerRepository repository;

    public CustomerController(EnergyCustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<EnergyCustomer> getCustomers() {
        return repository.findAll();
    }
}
