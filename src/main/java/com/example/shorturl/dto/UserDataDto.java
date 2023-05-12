package com.example.shorturl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {

    @JsonProperty("user")
    private String user;

    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("datetime")
    private ZonedDateTime dateTime;

    @JsonProperty("version")
    private int version;

    @JsonProperty("enable")
    private boolean enabled;

}
