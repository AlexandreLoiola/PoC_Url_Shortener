package com.AlexandreLoiols.ShortURLService.repository;

import com.AlexandreLoiols.ShortURLService.model.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<UrlModel, UUID> {
    @Query("SELECT u FROM UrlModel u WHERE u.isActive = true AND (u.originalUrl = :url OR u.shortUrl = :url)")
    Optional<UrlModel> findByUrl(@Param("url") String url);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UrlModel u WHERE u.shortUrl = :shortUrl")
    boolean existsByShortUrl(@Param("shortUrl") String shortUrl);
}