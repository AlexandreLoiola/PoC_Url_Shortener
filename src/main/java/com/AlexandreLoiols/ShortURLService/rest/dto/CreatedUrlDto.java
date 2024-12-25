package com.AlexandreLoiols.ShortURLService.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedUrlDto {
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime expiresAt;
}
