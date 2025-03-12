package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.RequirementRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RequirementResponse;
import org.example.quanlytuyendung.entity.RequirementEntity;

public interface RequirementService {
    ApiResponse<PageableResponse<RequirementResponse>> findAll(int page, int size,String search,String sort);

    ApiResponse<RequirementResponse> addRequirement(RequirementRequest requirementRequest);

    ApiResponse<RequirementResponse> updateRequirement(RequirementRequest requirementRequest);

    ApiResponse<RequirementResponse> getReuirement(int id);

    RequirementEntity deleteRequirement(int id);
}
