package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.BenifitRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.BenifitResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.BenifitEntity;

public interface BenifitService {
    ApiResponse<PageableResponse<BenifitResponse>> getBenifit(int page, int size, String search,String sort);

    ApiResponse<BenifitResponse> postBenifit(BenifitRequest benifitRequest);

    ApiResponse<BenifitResponse> updateBenifit(BenifitRequest benifitRequest);

    ApiResponse<BenifitResponse> getBenifitId(int id);

    BenifitEntity deleteBenifit(int id);
}
