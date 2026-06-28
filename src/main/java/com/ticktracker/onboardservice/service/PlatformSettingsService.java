package com.ticktracker.onboardservice.service;

import com.ticktracker.onboardservice.model.PlatformSettings;
import com.ticktracker.onboardservice.repo.PlatformSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformSettingsService {

    @Autowired
    private PlatformSettingsRepository platformSettingsRepository;

    public PlatformSettings getPlatformSettings()
    {
         return platformSettingsRepository.getById(1L);
    }
}
