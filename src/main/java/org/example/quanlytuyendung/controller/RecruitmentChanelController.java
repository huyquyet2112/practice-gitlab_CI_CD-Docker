package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.RecruitmentChanelRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentChanelResponse;
import org.example.quanlytuyendung.service.RecruitmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/recruitment-chanel")
public class RecruitmentChanelController {
    private final RecruitmentService recruitmentService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<RecruitmentChanelResponse>>> getRecruitmentChanel(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name
    ) {
        RecruitmentChanelResponse response = new RecruitmentChanelResponse();
        if (name != null && !name.trim().isEmpty()) {
            response.setName(name);
        }
        ApiResponse<PageableResponse<RecruitmentChanelResponse>> apiResponse =
                recruitmentService.findAllChanel(page, size, response);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<RecruitmentChanelResponse> addRecruitmentChanel(
            @RequestBody RecruitmentChanelRequest request) {
        RecruitmentChanelResponse response = recruitmentService.addRecruitmentChanel(request);
        return ResponseEntity.ok(response);
    }
}
