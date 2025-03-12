package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.RecruitmentRoundRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentRoundResponse;
import org.example.quanlytuyendung.entity.RecruitmentRoundEntity;
import org.example.quanlytuyendung.service.RecruitmentRoundService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/recruitment-round")
public class RecruitmentRoundController {
    private final RecruitmentRoundService recruitmentRoundService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<RecruitmentRoundResponse>>> getRecruitmentRounds(
            @RequestParam(defaultValue = "0" , required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(required = false)String search,
            @RequestParam (defaultValue = "createdAt:DESC")String sort
    ) {

        ApiResponse<PageableResponse<RecruitmentRoundResponse>> apiResponse = recruitmentRoundService.findRound(page,size,search,sort);
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping
    public ResponseEntity<ApiResponse<RecruitmentRoundResponse>> addRecruitmentRound(@RequestBody RecruitmentRoundRequest recruitmentRoundRequest) {
       ApiResponse<RecruitmentRoundResponse> recruitmentRoundResponse = recruitmentRoundService.addRecruitmentRound(recruitmentRoundRequest);
        return ResponseEntity.ok(recruitmentRoundResponse);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<RecruitmentRoundResponse>> updateRecruitmentRound(@RequestBody RecruitmentRoundRequest recruitmentRoundRequest) {
        ApiResponse<RecruitmentRoundResponse> response = recruitmentRoundService.updateRound(recruitmentRoundRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<RecruitmentRoundResponse>> getRecruitmentRoundById(@RequestParam int id) {
        ApiResponse<RecruitmentRoundResponse> response = recruitmentRoundService.getRecruitmentRound(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping
    public ResponseEntity<RecruitmentRoundEntity> deleteRecruitmentRoundById(@RequestParam int id) {
        RecruitmentRoundEntity recruitmentRoundEntity = recruitmentRoundService.deleteRound(id);
        return ResponseEntity.ok(recruitmentRoundEntity);
    }
}
