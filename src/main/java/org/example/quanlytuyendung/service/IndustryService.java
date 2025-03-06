package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.IndustryRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.IndustryResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.IndustryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IndustryService {



    ApiResponse<PageableResponse<IndustryResponse>> findAll(int page, int size,IndustryResponse industryResponse);

     IndustryResponse save(IndustryRequest industryRequest);

     IndustryResponse update(IndustryRequest industryRequest);

     ApiResponse<IndustryResponse> findIndustry(int id);



     IndustryEntity deleteIndustry(int id);
}
