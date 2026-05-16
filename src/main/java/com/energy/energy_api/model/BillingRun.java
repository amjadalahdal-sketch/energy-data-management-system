package com.energy.energy_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "BILLING_RUNS")
public class BillingRun {

    @Id
    @Column(name = "BILLING_RUN_ID")
    private Long billingRunId;

    @Column(name = "RUN_NAME")
    private String runName;

    @Column(name = "PERIOD_FROM")
    private LocalDate periodFrom;

    @Column(name = "PERIOD_TO")
    private LocalDate periodTo;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    public Long getBillingRunId() { return billingRunId; }
    public String getRunName() { return runName; }
    public LocalDate getPeriodFrom() { return periodFrom; }
    public LocalDate getPeriodTo() { return periodTo; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}