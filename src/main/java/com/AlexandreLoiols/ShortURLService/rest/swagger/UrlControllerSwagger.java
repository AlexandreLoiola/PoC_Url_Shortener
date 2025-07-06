package com.AlexandreLoiols.ShortURLService.rest.swagger;

import com.AlexandreLoiols.ShortURLService.rest.dto.CreatedUrlDto;
import com.AlexandreLoiols.ShortURLService.rest.dto.UrlDetailsDto;
import com.AlexandreLoiols.ShortURLService.rest.form.UrlForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "URL Management Controller", description = "Handles URL shortening and redirection operations")
public interface UrlControllerSwagger {

    @Operation(
            summary = "Retrieve paginated URL details",
            description = "Fetches a paginated list of URL details."
    )
    @ApiResponse(responseCode = "200", description = "Page of URL details successfully retrieved")
    @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    @ApiResponse(responseCode = "500", description = "Server error")
    @GetMapping("/details")
    ResponseEntity<Page<UrlDetailsDto>> getUrl(Pageable pageable);

    @Operation(
            summary = "Retrieve URL details by its shortened form",
            description = "Fetches details of the original URL based on the provided shortened URL."
    )
    @ApiResponse(responseCode = "200", description = "URL details successfully retrieved")
    @ApiResponse(responseCode = "404", description = "URL not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    @GetMapping("/details/{url}")
    ResponseEntity<UrlDetailsDto> getUrl(@PathVariable("url") String url);

    @Operation(
            summary = "Shorten a URL",
            description = "Creates a shortened version of the provided URL."
    )
    @ApiResponse(responseCode = "201", description = "URL successfully shortened")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "500", description = "Server error")
    @PostMapping("/shorten")
    ResponseEntity<CreatedUrlDto> shortenUrl(@Valid @RequestBody UrlForm form);

    @Operation(
            summary = "Deactivate a shortened URL",
            description = "Deactivates the specified shortened URL, preventing further redirections."
    )
    @ApiResponse(responseCode = "200", description = "Shortened URL successfully deactivated")
    @ApiResponse(responseCode = "404", description = "URL not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    @PutMapping("/deactivate/{url}")
    ResponseEntity<UrlDetailsDto> deactivateShortUrl(@PathVariable("url") String url);
}
