package com.energy.energy_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BILLING_ITEMS")
public class BillingItem {

    @Id
    @Column(name = "BILLING_ITEM_ID")
    private Long billingItemId;

    @Column(name = "BILLING_RUN_ID")
    private Long billingRunId;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "MP_ID")
    private Long mpId;

    @Column(name = "TARIFF_ID")
    private Long tariffId;

    @Column(name = "TOTAL_CONSUMPTION")
    private Double totalConsumption;

    @Column(name = "PRICE_PER_KWH")
    private Double pricePerKwh;

    @Column(name = "BASE_FEE")
    private Double baseFee;

    @Column(name = "ENERGY_AMOUNT")
    private Double energyAmount;

    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    public Long getBillingItemId() { return billingItemId; }
    public Long getBillingRunId() { return billingRunId; }
    public Long getCustomerId() { return customerId; }
    public Long getMpId() { return mpId; }
    public Long getTariffId() { return tariffId; }
    public Double getTotalConsumption() { return totalConsumption; }
    public Double getPricePerKwh() { return pricePerKwh; }
    public Double getBaseFee() { return baseFee; }
    public Double getEnergyAmount() { return energyAmount; }
    public Double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}