package com.energy.energy_api.controller;

import com.energy.energy_api.dto.ApiResponse;
import com.energy.energy_api.dto.CreateBillingRunRequest;
import com.energy.energy_api.model.BillingRun;
import com.energy.energy_api.repository.BillingRunRepository;
import com.energy.energy_api.service.BillingRunService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing-runs")
@CrossOrigin(origins = {
    "http://localhost:4200",
    "https://angular.amjadalahdal.com"
})
public class BillingRunController {

    private final BillingRunRepository repository;
    private final BillingRunService billingRunService;

    public BillingRunController(
            BillingRunRepository repository,
            BillingRunService billingRunService
    ) {
        this.repository = repository;
        this.billingRunService = billingRunService;
    }

    @GetMapping
    public List<BillingRun> getBillingRuns() {
        return repository.findAll();
    }

    @PostMapping("/{id}/process")
    public ApiResponse processBillingRun(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") Long createdBy
    ) {
        billingRunService.processBillingRun(id, createdBy);
        return new ApiResponse("Billing run processed successfully");
    }
    
    @PostMapping("/{id}/finalize")
    public ApiResponse finalizeBillingRun(@PathVariable Long id) {
        billingRunService.finalizeBillingRun(id);
        return new ApiResponse("Billing run finalized successfully");
    }

    @PostMapping
    public ApiResponse createBillingRun(@RequestBody CreateBillingRunRequest request) {
        billingRunService.createBillingRun(
            request.getRunName(),
            request.getPeriodFrom(),
            request.getPeriodTo(),
            request.getCreatedBy()
        );

        return new ApiResponse("Billing run created successfully");
    }
}
