package com.example.shorturl.config;

import com.google.common.cache.CacheBuilder;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    private static final int CACHE_TIMEOUT_SECS = 30;
    public static final String CACHE_NAME_USERS = "users";

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {
            @Override
            protected @NotNull Cache createConcurrentMapCache(@NotNull String name) {
                return new ConcurrentMapCache(name,
                    CacheBuilder.newBuilder().expireAfterWrite(CACHE_TIMEOUT_SECS, TimeUnit.SECONDS)
                        .maximumSize(100).build().asMap(), false);
            }
        };

        cacheManager.setCacheNames(List.of(CACHE_NAME_USERS));
        return cacheManager;
    }

}
