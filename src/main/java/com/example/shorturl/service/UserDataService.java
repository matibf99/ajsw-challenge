package com.example.shorturl.service;

import com.example.shorturl.dto.ShortUrlDto;
import com.example.shorturl.dto.UserDataDto;

public interface UserDataService {

    ShortUrlDto generateShortUrl(String email);

    UserDataDto getUserData(long shortUrl);

}
