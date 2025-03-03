package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.response.JobPositionResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;

public interface JobpositionService {
    PageableResponse<JobPositionResponse> findAll(int page, int size);
}
