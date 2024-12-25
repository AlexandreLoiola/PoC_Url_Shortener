package com.AlexandreLoiols.ShortURLService.service;

import com.AlexandreLoiols.ShortURLService.exception.UrlNotFoundException;
import com.AlexandreLoiols.ShortURLService.exception.UrlShorteningException;
import com.AlexandreLoiols.ShortURLService.mapper.UrlMapper;
import com.AlexandreLoiols.ShortURLService.model.UrlModel;
import com.AlexandreLoiols.ShortURLService.repository.UrlRepository;
import com.AlexandreLoiols.ShortURLService.rest.dto.CreatedUrlDto;
import com.AlexandreLoiols.ShortURLService.rest.dto.UrlDetailsDto;
import com.AlexandreLoiols.ShortURLService.rest.form.UrlForm;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
public class UrlService {
    private static final Logger logger = LoggerFactory.getLogger(UrlService.class);
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;

    public UrlService(UrlRepository urlRepository, UrlMapper urlMapper) {
        this.urlRepository = urlRepository;
        this.urlMapper = urlMapper;
    }

    public CreatedUrlDto shortenUrl(UrlForm form) {
        UrlModel newModel = urlMapper.formToUrlModel(form);
        String shortUrl = generateShortenedUrlWith12Characters(form.getOriginalUrl());
        if (urlRepository.existsByShortUrl(shortUrl)) {
            logger.warn("Shortened URL conflict detected for: {}. Regenerating a new shortened URL.", form.getOriginalUrl());
            shortUrl = generateShortenedUrlWith12Characters(form.getOriginalUrl());
        }
        newModel.setShortUrl(shortUrl);
        newModel.setCreatedAt(LocalDateTime.now());
        try {
            urlRepository.save(newModel);
            return urlMapper.modelToCreatedUrlDto(newModel);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error saving the shortened URL: Data integrity violation", e);
            throw new UrlShorteningException("Error saving the shortened URL, integrity violation occurred.");
        } catch (Exception e) {
            logger.error("Error saving the shortened URL", e);
            throw new UrlShorteningException("An unexpected error occurred while shortening the URL.");
        }
    }

    public String generateShortenedUrlWith12Characters(String url) {
        try {
            // Gerar hash SHA-256 da url
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(url.getBytes());
            // Converte o hash para uma String hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.substring(0, 12);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UrlModel findUrlModel(String url) {
        return urlRepository.findByUrl(url)
                .orElseThrow(() -> {
                    String errorMessage = String.format("URL '%s' not found in the database", url);
                    logger.error(errorMessage);
                    return new UrlNotFoundException(errorMessage);
                });
    }

    public UrlDetailsDto getUrlDetails(String url) {
        UrlModel model = findUrlModel(url);
        logger.info("Successfully retrieved details for URL: {}", url);
        return urlMapper.modelToUrlDetailsDto(model);
    }

    public Page<UrlDetailsDto> getUrlDetailsPage(Pageable pageable) {
        logger.info("Fetching paginated URL details. Page request: {}", pageable);
        Page<UrlModel> urlPage = urlRepository.findAll(pageable);
        logger.info("Successfully retrieved {} URL(s) from the database.", urlPage.getNumberOfElements());
        return urlPage.map(urlMapper::modelToUrlDetailsDto);
    }

    public String redirectUrl(String shortUrl) {
        logger.info("Redirecting URL for short URL: {}", shortUrl);
        UrlModel model = findUrlModel(shortUrl);
        incrementClickCount(model);
        validateExpiration(model);
        return model.getOriginalUrl();
    }

    public void incrementClickCount(UrlModel model) {
        try {
            model.setClickCount(model.getClickCount() + 1);
            urlRepository.save(model);
            logger.info("Click count for URL '{}' incremented to {}", model.getShortUrl(), model.getClickCount());
        } catch (Exception e) {
            logger.error("Failed to increment click count for URL '{}'. Error: {}", model.getShortUrl(), e.getMessage(), e);
            throw new RuntimeException("Failed to increment click count for URL: " + model.getShortUrl(), e);
        }
    }

    public void validateExpiration(UrlModel model) {
        if (model.getExpiresAt().isBefore(LocalDateTime.now())) {
            logger.warn("The URL '{}' has expired.", model.getShortUrl());
            throw new UrlNotFoundException("The URL has expired and is no longer valid.");
        }
    }

    public UrlDetailsDto deactivateExpiredUrls(String url) {
        UrlModel model = findUrlModel(url);
        model.setActive(false);
        try {
            urlRepository.save(model);
            logger.info("Successfully deactivated URL: {}", url);
            return urlMapper.modelToUrlDetailsDto(model);
        } catch (Exception e) {
            logger.error("Unexpected error while deactivating URL: {}", url, e);
            throw new RuntimeException("Unexpected error while deactivating the URL: " + url);
        }
    }
}