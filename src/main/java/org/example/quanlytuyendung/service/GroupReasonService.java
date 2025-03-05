package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.GroupReasonRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.GroupReasonResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.GroupReasonEntity;

public interface GroupReasonService {
    ApiResponse<PageableResponse<GroupReasonResponse>> findAll(int page, int size);

    GroupReasonResponse addGroupReason(GroupReasonRequest groupReasonRequest);

    GroupReasonResponse updateGroupReason(GroupReasonRequest groupReasonRequest);

    ApiResponse<GroupReasonResponse> detailsGroupReason(int id);

    GroupReasonEntity deleteGroupReason(int id);
}
