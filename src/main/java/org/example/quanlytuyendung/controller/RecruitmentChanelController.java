package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.RecruitmentChanelRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentChanelResponse;
import org.example.quanlytuyendung.entity.RecruitmentChanelEntity;
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
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "createdAt:DESC") String sort
    ) {

        ApiResponse<PageableResponse<RecruitmentChanelResponse>> apiResponse =
                recruitmentService.findAllChanel(page, size, search,sort);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RecruitmentChanelResponse>> addRecruitmentChanel(
            @RequestBody RecruitmentChanelRequest request) {
       ApiResponse <RecruitmentChanelResponse> response = recruitmentService.addRecruitmentChanel(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<RecruitmentChanelResponse>> getRecruitmentChanel(@RequestParam int id) {
        ApiResponse <RecruitmentChanelResponse>  response = recruitmentService.findRecruimentChanel(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<RecruitmentChanelResponse>> updateRecruitmentChanel(@RequestBody RecruitmentChanelRequest request) {
        ApiResponse <RecruitmentChanelResponse>  response = recruitmentService.updateRecruitmentChanel(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping
    public ResponseEntity<RecruitmentChanelEntity> delteRecruitmentChanel(@RequestParam int id) {
        RecruitmentChanelEntity recruitmentChanelEntity = recruitmentService.deleteRecruitmentChanel(id);
        return ResponseEntity.ok(recruitmentChanelEntity);
    }
}
