package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.WorkTypeRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.WorkTypeResponse;
import org.example.quanlytuyendung.entity.WorkTypeEntity;

public interface WorkTypeService {
    ApiResponse<PageableResponse<WorkTypeResponse>> getWorkTypeList(int page, int size, WorkTypeResponse workTypeResponse);

    ApiResponse<WorkTypeResponse> addWorkType(WorkTypeRequest workTypeRequest);

    ApiResponse<WorkTypeResponse> updateWorkType(WorkTypeRequest workTypeRequest);

    ApiResponse<WorkTypeResponse> getWorkType(int id);

    WorkTypeEntity deleteWorkType(int id);
}
