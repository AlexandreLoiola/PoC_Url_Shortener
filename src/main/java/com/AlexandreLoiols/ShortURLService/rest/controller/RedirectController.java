package com.AlexandreLoiols.ShortURLService.rest.controller;

import com.AlexandreLoiols.ShortURLService.rest.swagger.RedirectControllerSwagger;
import com.AlexandreLoiols.ShortURLService.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/")
public class RedirectController implements RedirectControllerSwagger {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectUrl(@PathVariable("shortUrl") String shortUrl) {
        String url = urlService.redirectUrl(shortUrl);
        return ResponseEntity.status(302).location(URI.create(url)).build();
    }
}
