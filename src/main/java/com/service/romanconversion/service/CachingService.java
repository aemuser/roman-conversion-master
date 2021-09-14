package com.service.romanconversion.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Caching Service Class
 */
@Service
@Slf4j
public class CachingService {
    @Autowired
    CacheManager cacheManager;

    /**
     * Method to find all caches and clear them
     */
    public void evictAllCaches() {
        cacheManager.getCacheNames()
                .forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
        log.info("All cache has been cleared");
    }
}
