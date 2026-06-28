package com.ticktracker.onboardservice.repo;

import com.ticktracker.onboardservice.model.PlatformSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformSettingsRepository extends JpaRepository< PlatformSettings, Long> {
}
