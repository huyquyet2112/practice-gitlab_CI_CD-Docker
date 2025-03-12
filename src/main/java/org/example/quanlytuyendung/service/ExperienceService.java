package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.ExperienceRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.ExperienceResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.ExperienceEntity;

public interface ExperienceService {
    ApiResponse<PageableResponse<ExperienceResponse>> findAll(int page, int size,String search,String sort);

   ApiResponse <ExperienceResponse> addExperience(ExperienceRequest experienceRequest);

   ApiResponse <ExperienceResponse> updateExperience(ExperienceRequest experienceRequest);

    ApiResponse<ExperienceResponse> getExperience(int id);

    ExperienceEntity deleteExperience(int id);
}
