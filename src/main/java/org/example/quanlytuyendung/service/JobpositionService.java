package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.JobPositionRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.JobPositionResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.JobPositionEntity;

public interface JobpositionService {
    ApiResponse <PageableResponse<JobPositionResponse>> findAll(int page, int size,String search,String sort);

    ApiResponse<JobPositionResponse> addJobPosition(JobPositionRequest request);

    JobPositionResponse updatePosition(JobPositionRequest request);

    ApiResponse<JobPositionResponse> findPosition(int id);

    JobPositionEntity deleteJobPosittion(int id);
}
