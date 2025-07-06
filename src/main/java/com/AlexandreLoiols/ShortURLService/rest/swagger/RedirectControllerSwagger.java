package com.AlexandreLoiols.ShortURLService.rest.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Redirect-Controlle", description = "Handles URL redirection based on short URLs")
public interface RedirectControllerSwagger {

    @Operation(
            summary = "Redirect to the original URL",
            description =  "Receiver a short URL iidentifier, retrieves the original URL, and redirects it"
    )
    @ApiResponse(responseCode = "302", description = "Redirect successful")
    @ApiResponse(responseCode = "404", description = "Short URL not found")
    @GetMapping("/{shortUrl}")
    ResponseEntity<Void> redirectUrl(
            @PathVariable("shortUrl") String shortUrl
    );
}