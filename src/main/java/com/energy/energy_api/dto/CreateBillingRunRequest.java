package com.energy.energy_api.dto;

import java.time.LocalDate;

public class CreateBillingRunRequest {

    private String runName;
    private LocalDate periodFrom;
    private LocalDate periodTo;
    private Long createdBy;

    public String getRunName() {
        return runName;
    }

    public LocalDate getPeriodFrom() {
        return periodFrom;
    }

    public LocalDate getPeriodTo() {
        return periodTo;
    }

    public Long getCreatedBy() {
        return createdBy;
    }
}
