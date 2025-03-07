package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.RecruitmentRoundRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentRoundResponse;
import org.example.quanlytuyendung.entity.RecruitmentRoundEntity;

public interface RecruitmentRoundService {
    ApiResponse<PageableResponse<RecruitmentRoundResponse>> findRound(int page, int size, RecruitmentRoundResponse recruitmentRoundResponse);

    RecruitmentRoundResponse addRecruitmentRound(RecruitmentRoundRequest recruitmentRoundRequest);

    ApiResponse<RecruitmentRoundResponse> updateRound(RecruitmentRoundRequest recruitmentRoundRequest);

    ApiResponse<RecruitmentRoundResponse> getRecruitmentRound(int id);

    RecruitmentRoundEntity deleteRound(int id);
}
