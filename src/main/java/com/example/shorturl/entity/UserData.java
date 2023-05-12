package com.example.shorturl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;


@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "user", unique = true, nullable = false)
    private String user;

    @NaturalId
    @Column(name = "short_url", unique = true, nullable = false)
    private long shortUrl;

    @Column(name = "datetime", nullable = false)
    private ZonedDateTime dateTime;

    @Column(name = "version", nullable = false)
    private int version;

    @Column(name = "enable", nullable = false)
    private boolean enabled;

}
