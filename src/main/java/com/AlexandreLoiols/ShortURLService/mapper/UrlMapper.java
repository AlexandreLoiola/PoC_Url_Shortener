package com.AlexandreLoiols.ShortURLService.mapper;

import com.AlexandreLoiols.ShortURLService.model.UrlModel;
import com.AlexandreLoiols.ShortURLService.rest.dto.CreatedUrlDto;
import com.AlexandreLoiols.ShortURLService.rest.dto.UrlDetailsDto;
import com.AlexandreLoiols.ShortURLService.rest.form.UrlForm;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {

    public UrlModel formToUrlModel(UrlForm urlForm) {
        UrlModel urlModel = new UrlModel();
        urlModel.setOriginalUrl(urlForm.getOriginalUrl());
        urlModel.setExpiresAt(urlForm.getExpiresAt());
        return urlModel;
    }

    public UrlDetailsDto modelToUrlDetailsDto(UrlModel urlModel) {
        UrlDetailsDto urlDetailsDto = new UrlDetailsDto();
        urlDetailsDto.setOriginalUrl(urlModel.getOriginalUrl());
        urlDetailsDto.setShortUrl(urlModel.getShortUrl());
        urlDetailsDto.setCreatedAt(urlModel.getCreatedAt());
        urlDetailsDto.setExpiresAt(urlModel.getExpiresAt());
        urlDetailsDto.setClickCount(urlModel.getClickCount());
        return urlDetailsDto;
    }

    public CreatedUrlDto modelToCreatedUrlDto(UrlModel urlModel) {
        CreatedUrlDto createdUrlDto = new CreatedUrlDto();
        createdUrlDto.setOriginalUrl(urlModel.getOriginalUrl());
        createdUrlDto.setShortUrl(urlModel.getShortUrl());
        createdUrlDto.setExpiresAt(urlModel.getExpiresAt());
        return createdUrlDto;
    }
}
