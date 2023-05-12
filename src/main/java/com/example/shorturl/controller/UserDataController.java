package com.example.shorturl.controller;

import com.example.shorturl.dto.ShortUrlDto;
import com.example.shorturl.dto.UserDataDto;
import com.example.shorturl.service.UserDataService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserDataController {

    private final UserDataService service;

    @Autowired
    public UserDataController(UserDataService service) {
        this.service = service;
    }

    @GetMapping("/access/{email}")
    public ResponseEntity<ShortUrlDto> generateShortUrl(@PathVariable @Email String email) {
        ShortUrlDto dto = service.generateShortUrl(email);

        return (dto != null) ? ResponseEntity.ok(dto)
            : ResponseEntity.internalServerError().build();
    }

    @GetMapping("{shortUrl}")
    public ResponseEntity<UserDataDto> getUrlData(@PathVariable @Positive long shortUrl) {
        UserDataDto dto = service.getUserData(shortUrl);

        return (dto != null) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

}
