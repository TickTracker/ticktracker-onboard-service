package com.ticktracker.onboardservice.config;

import com.ticktracker.onboardservice.model.PlatformSettings;
import com.ticktracker.onboardservice.repo.PlatformSettingsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PlatformSettingsInitializer implements CommandLineRunner {

    /**
     * This class runs automatically once every time the Spring Boot application starts.
     *
     * Why CommandLineRunner?
     * ----------------------
     * Spring Boot calls the run() method after:
     *  - All Beans are created
     *  - Database connection is established
     *  - Hibernate has created/updated all tables
     *
     * We use it to initialize application data that should always exist.
     *
     * Why is this needed?
     * -------------------
     * Hibernate creates the PlatformSettings table,
     * but it DOES NOT insert any default rows.
     *
     * Since our application always requires exactly ONE PlatformSettings record,
     * we create it automatically on the first startup.
     *
     * Later application startups won't insert another row because we first check
     * whether the table already contains data.
     *
     * This table stores global platform configuration such as:
     *  - Registration Enabled
     *  - Admin Approval Required
     *  - Maintenance Mode
     *
     * There should always be only ONE row in this table.
     */
    private final PlatformSettingsRepository repository;

    public PlatformSettingsInitializer(PlatformSettingsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {

            PlatformSettings settings = new PlatformSettings();

            settings.setId(1L);
            settings.setApprovalRequired(false);
            settings.setRegistrationEnabled(true);
            settings.setMaintainanceMode(false);

            repository.save(settings);
        }
    }
}