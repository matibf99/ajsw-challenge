package com.example.shorturl.mapper;


import com.example.shorturl.dto.ShortUrlDto;
import com.example.shorturl.dto.UserDataDto;
import com.example.shorturl.entity.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserDataMapper {

    @Named("convertShortUrlToUrl")
    static String convertShortUrlToUrl(long shortUrl) {
        return "http://localhost:8080/" + shortUrl;
    }

    @Mapping(source = "shortUrl", target = "url", qualifiedByName = "convertShortUrlToUrl")
    ShortUrlDto shortUrlDtoMapper(UserData userData);

    UserDataDto userDataDtoMapper(UserData userData);

}
