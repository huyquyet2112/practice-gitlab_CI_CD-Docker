package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.GroupReasonResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.repository.GroupReasonRepository;
import org.example.quanlytuyendung.service.GroupReasonService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupReasonServiceImpl implements GroupReasonService {

    private final GroupReasonRepository groupReasonRepository;



    @Override
    public ApiResponse<PageableResponse<GroupReasonResponse>> findAll(int page, int size) {
        return null;
    }
}
