package com.AlexandreLoiols.ShortURLService.rest.form;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlForm {
    @NotNull(message = "The original URL cannot be null.")
    @NotEmpty(message = "The original URL cannot be empty.")
    @Size(max = 2048, message = "The original URL cannot exceed 2048 characters.")
    @Pattern(regexp = "^(http|https)://.*$", message = "The original URL must be a valid URL (http or https).")
    private String originalUrl;

    @Future(message = "The expiration date must be a future date.")
    private LocalDateTime expiresAt;
}