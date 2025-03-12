package org.example.quanlytuyendung.service;


import org.example.quanlytuyendung.dto.request.RecruitmentChanelRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentChanelResponse;
import org.example.quanlytuyendung.entity.RecruitmentChanelEntity;

public interface RecruitmentService {
    ApiResponse<PageableResponse<RecruitmentChanelResponse>> findAllChanel(int page, int size,String search,String sort);

   ApiResponse<RecruitmentChanelResponse> addRecruitmentChanel(RecruitmentChanelRequest request);

    ApiResponse<RecruitmentChanelResponse> findRecruimentChanel(int id);

    ApiResponse<RecruitmentChanelResponse> updateRecruitmentChanel(RecruitmentChanelRequest request);

    RecruitmentChanelEntity deleteRecruitmentChanel(int id);
}
