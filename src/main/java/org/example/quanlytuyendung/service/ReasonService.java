package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.ReasonRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.ReasonResponse;
import org.example.quanlytuyendung.entity.ReasonEntity;

public interface ReasonService {
    ApiResponse<PageableResponse<ReasonResponse>> findAllReason(int page, int size,String search,String sort);

    ApiResponse<ReasonResponse> addReason(ReasonRequest reasonRequest);

    ApiResponse<ReasonResponse> updateReason(ReasonRequest reasonRequest);

    ApiResponse<ReasonResponse> findReason(Integer id);

    ReasonEntity deleteReason(Integer id);
}
