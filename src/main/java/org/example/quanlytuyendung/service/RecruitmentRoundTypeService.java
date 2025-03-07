package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.RecruitmentRoundTypeRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentRoundTypeResponse;
import org.example.quanlytuyendung.entity.RecruitmentRoundTypeEntity;

public interface RecruitmentRoundTypeService {
    ApiResponse<PageableResponse<RecruitmentRoundTypeResponse>> findAll(int page, int size, RecruitmentRoundTypeResponse recruitmentRoundTypeResponse);

    RecruitmentRoundTypeResponse addRoundType(RecruitmentRoundTypeRequest request);

    RecruitmentRoundTypeResponse updateRoundType(RecruitmentRoundTypeRequest request);

    ApiResponse<RecruitmentRoundTypeResponse> getRoundType(int id);

    RecruitmentRoundTypeEntity deleteRoundType(int id);
}
