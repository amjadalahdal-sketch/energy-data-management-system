package com.energy.energy_api.controller;

import com.energy.energy_api.model.BillingItem;
import com.energy.energy_api.repository.BillingItemRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing-items")
@CrossOrigin(origins = {
    "http://localhost:4200",
    "https://angular.amjadalahdal.com"
})
public class BillingItemController {

    private final BillingItemRepository repository;

    public BillingItemController(BillingItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<BillingItem> getBillingItems() {
        return repository.findAll();
    }
}
