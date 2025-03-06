package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.CandicateSourceRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.CandicateSourceResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.CandicateSourceEntity;

public interface CandicateSourceService {
    ApiResponse<PageableResponse<CandicateSourceResponse>> findAll(int page, int size, CandicateSourceResponse candicateSourceResponse);

    CandicateSourceResponse addCandicateSource(CandicateSourceRequest candicateSourceRequest);

    CandicateSourceResponse updateCandicateSource(CandicateSourceRequest candicateSourceRequest);

    CandicateSourceResponse getCandicateSource(int id);

    CandicateSourceEntity deleteCandicateSource(int id);
}
