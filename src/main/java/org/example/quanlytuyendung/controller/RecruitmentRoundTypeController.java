package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.RecruitmentRoundTypeRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentRoundTypeResponse;
import org.example.quanlytuyendung.entity.RecruitmentRoundTypeEntity;
import org.example.quanlytuyendung.service.RecruitmentRoundTypeService;
import org.example.quanlytuyendung.service.RecruitmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/recruitment-round-type")
public class RecruitmentRoundTypeController {
    private final RecruitmentRoundTypeService recruitmentRoundTypeService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<RecruitmentRoundTypeResponse>>> getRecruitmentRoundTypeList(
            @RequestParam(defaultValue = "0" , required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(required = false)String search,
            @RequestParam (defaultValue = "createdAt:DESC")String sort
    ){

        ApiResponse<PageableResponse<RecruitmentRoundTypeResponse>> response = recruitmentRoundTypeService.findAll(page,size,search,sort);
        return ResponseEntity.ok(response);

    }
    @PostMapping
    public ResponseEntity<ApiResponse<RecruitmentRoundTypeResponse>> addRecruitmentRoundType(@RequestBody RecruitmentRoundTypeRequest request){
        ApiResponse<RecruitmentRoundTypeResponse> recruitmentRoundTypeResponse = recruitmentRoundTypeService.addRoundType(request);
        return ResponseEntity.ok(recruitmentRoundTypeResponse);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<RecruitmentRoundTypeResponse>> updateRecruitmentRoundType(@RequestBody RecruitmentRoundTypeRequest request){
        ApiResponse<RecruitmentRoundTypeResponse> recruitmentRoundTypeResponse = recruitmentRoundTypeService.updateRoundType(request);
        return ResponseEntity.ok(recruitmentRoundTypeResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<RecruitmentRoundTypeResponse>> getRecruitmentRoundTypeList(@RequestParam  int id){
        ApiResponse<RecruitmentRoundTypeResponse> response = recruitmentRoundTypeService.getRoundType(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping
    public ResponseEntity<RecruitmentRoundTypeEntity> deleteRecruitmentRoundType(int id) {
        RecruitmentRoundTypeEntity recruitmentRoundTypeEntity = recruitmentRoundTypeService.deleteRoundType(id);
        return ResponseEntity.ok(recruitmentRoundTypeEntity);
    }

}
