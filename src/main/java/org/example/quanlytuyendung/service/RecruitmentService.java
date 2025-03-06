package org.example.quanlytuyendung.service;


import org.example.quanlytuyendung.dto.request.RecruitmentChanelRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentChanelResponse;

public interface RecruitmentService {
    ApiResponse<PageableResponse<RecruitmentChanelResponse>> findAllChanel(int page, int size, RecruitmentChanelResponse response);

   RecruitmentChanelResponse addRecruitmentChanel(RecruitmentChanelRequest request);
}
