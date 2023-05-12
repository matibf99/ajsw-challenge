package com.example.shorturl.repository;

import com.example.shorturl.entity.UserData;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, UUID> {
    
    UserData getByShortUrl(long shortUrl);

    boolean existsByShortUrl(long shortUrl);

}
