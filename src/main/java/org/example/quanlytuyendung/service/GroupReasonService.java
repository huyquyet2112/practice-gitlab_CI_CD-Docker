package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.GroupReasonResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;

public interface GroupReasonService {
    ApiResponse<PageableResponse<GroupReasonResponse>> findAll(int page, int size);
}
