package com.energy.energy_api.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BillingRunService {

    private final JdbcTemplate jdbcTemplate;

    public BillingRunService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void processBillingRun(Long billingRunId, Long createdBy) {
        jdbcTemplate.update(
            "BEGIN PKG_ENERGY_BILLING.PROCESS_BILLING_RUN(?, ?); END;",
            billingRunId,
            createdBy
        );
    }

    public void createBillingRun(String runName, java.time.LocalDate periodFrom, java.time.LocalDate periodTo, Long createdBy) {
        jdbcTemplate.update(
            "BEGIN PKG_ENERGY_BILLING.CREATE_BILLING_RUN(?, ?, ?, ?); END;",
            runName,
            java.sql.Date.valueOf(periodFrom),
            java.sql.Date.valueOf(periodTo),
            createdBy
        );
    }

    public void finalizeBillingRun(Long billingRunId) {
        jdbcTemplate.update(
            "BEGIN PKG_ENERGY_BILLING.FINALIZE_BILLING_RUN(?); END;",
            billingRunId
        );
    }
    
}