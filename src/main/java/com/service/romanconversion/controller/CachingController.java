package com.service.romanconversion.controller;

import com.service.romanconversion.service.CachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Class for Cache Management
 */
@RestController
public class CachingController {

    @Autowired
    CachingService cachingService;

    @GetMapping("clearCaches")
    public void clearAllCaches() {
        cachingService.evictAllCaches();
    }
}