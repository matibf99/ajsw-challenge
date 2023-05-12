package com.example.shorturl.service;

import static com.example.shorturl.config.CacheConfig.CACHE_NAME_USERS;

import com.example.shorturl.dto.ShortUrlDto;
import com.example.shorturl.dto.UserDataDto;
import com.example.shorturl.entity.UserData;
import com.example.shorturl.mapper.UserDataMapper;
import com.example.shorturl.repository.UserDataRepository;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImp implements UserDataService {

    private static final Logger logger = LoggerFactory.getLogger(UserDataServiceImp.class);

    private final UserDataRepository repository;
    private final UserDataMapper mapper;
    private final CacheManager cacheManager;

    @Autowired
    public UserDataServiceImp(UserDataRepository repository, UserDataMapper mapper,
        CacheManager cacheManager) {
        this.repository = repository;
        this.mapper = mapper;
        this.cacheManager = cacheManager;
    }

    @Override
    public ShortUrlDto generateShortUrl(String email) {
        UserData userData = UserData.builder()
            .user(email)
            .uuid(UUID.randomUUID())
            .shortUrl(createShortUrlNumber())
            .dateTime(ZonedDateTime.now())
            .version(1)
            .enabled(true)
            .build();

        repository.save(userData);

        Objects.requireNonNull(cacheManager.getCache(CACHE_NAME_USERS))
            .putIfAbsent(userData.getShortUrl(), userData);

        logger.info(
            String.format("Cache: added key '%d' from user '%s' to cache", userData.getShortUrl(),
                userData.getUser()));

        return mapper.shortUrlDtoMapper(userData);
    }

    @Override
    public UserDataDto getUserData(long shortUrl) {
        UserData userData = Objects.requireNonNull(cacheManager.getCache(CACHE_NAME_USERS))
            .get(shortUrl, UserData.class);

        if (userData == null) {
            logger.info(String.format("Cache: key '%d' not found in cache", shortUrl));
            userData = repository.getByShortUrl(shortUrl);
        } else {
            logger.info(String.format("Cache: key '%d' found in cache", shortUrl));
        }

        return mapper.userDataDtoMapper(userData);
    }

    private long createShortUrlNumber() {
        long shortUrl;
        do {
            shortUrl = (new Random()).nextInt(900000) + 100000L;
        } while (repository.existsByShortUrl(shortUrl));

        return shortUrl;
    }

}
