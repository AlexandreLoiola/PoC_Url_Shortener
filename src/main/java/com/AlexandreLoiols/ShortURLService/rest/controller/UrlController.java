package com.AlexandreLoiols.ShortURLService.rest.controller;

import com.AlexandreLoiols.ShortURLService.rest.dto.CreatedUrlDto;
import com.AlexandreLoiols.ShortURLService.rest.dto.UrlDetailsDto;
import com.AlexandreLoiols.ShortURLService.rest.form.UrlForm;
import com.AlexandreLoiols.ShortURLService.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/details")
    public ResponseEntity<Page<UrlDetailsDto>> getUrl(
            Pageable pageable
    ) {
        Page<UrlDetailsDto> dtoPage = urlService.getUrlDetailsPage(pageable);
        return ResponseEntity.status(200).body(dtoPage);
    }

    @GetMapping("/details/{url}")
    public ResponseEntity<UrlDetailsDto> getUrl(
            @PathVariable("url") String url
    ) {
        UrlDetailsDto dto = urlService.getUrlDetails(url);
        return ResponseEntity.status(200).body(dto);
    }

    @PostMapping("/shorten")
    public ResponseEntity<CreatedUrlDto> shortenUrl(
            @Valid @RequestBody UrlForm form
    ) {
        CreatedUrlDto dto = urlService.shortenUrl(form);
        return  ResponseEntity.status(201).body(dto);
    }

    @PutMapping("/deactivate/{url}")
    public ResponseEntity<UrlDetailsDto> deactivateShortUrl(
            @PathVariable("url") String url
    ) {
        UrlDetailsDto dto = urlService.deactivateExpiredUrls(url);
        return ResponseEntity.status(200).body(dto);
    }
}