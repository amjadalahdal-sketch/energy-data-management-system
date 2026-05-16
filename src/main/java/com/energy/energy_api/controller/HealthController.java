package com.energy.energy_api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {
    "http://localhost:4200",
    "https://angular.amjadalahdal.com"
})
public class HealthController {

    @GetMapping("/api/health")
    public String health() {
        return "Energy API is running";
    }
}
