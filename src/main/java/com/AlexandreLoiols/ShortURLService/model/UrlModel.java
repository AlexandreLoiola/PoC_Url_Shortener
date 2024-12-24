package com.AlexandreLoiols.ShortURLService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@Entity
@Table(name = "TB_URL")
public class UrlModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, length = 36)
    private UUID id;

    @Column(name = "original_url", nullable = false, unique = true, columnDefinition = "TEXT")
    private String originalUrl;

    @Column(name = "short_url", nullable = false, unique = true, length = 50)
    private String shortUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "click_count", nullable = false)
    private Integer clickCount = 0;
}
