package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.RequirementRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RequirementResponse;
import org.example.quanlytuyendung.entity.RequirementEntity;
import org.example.quanlytuyendung.service.RequirementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/requirement")
public class RequirementController {
    private final RequirementService requirementService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<RequirementResponse>>> getRequirements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "createdAt:DESC") String sort
    ) {
        ApiResponse<PageableResponse<RequirementResponse>> apiResponse = requirementService.findAll(page,size,search,sort);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<ApiResponse<RequirementResponse>> createRequirement(@RequestBody RequirementRequest requirementRequest) {
       ApiResponse <RequirementResponse> requirementResponse = requirementService.addRequirement(requirementRequest);
        return new ResponseEntity<>(requirementResponse, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<RequirementResponse>> updateRequirement(@RequestBody RequirementRequest requirementRequest) {
        ApiResponse <RequirementResponse> requirementResponse = requirementService.updateRequirement(requirementRequest);
        return new ResponseEntity<>(requirementResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<RequirementResponse>> getRequirement(@RequestParam int id) {
        ApiResponse<RequirementResponse> responseApiResponse = requirementService.getReuirement(id);
        return new ResponseEntity<>(responseApiResponse, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<RequirementEntity> removeRequirement(@RequestParam int id) {
        RequirementEntity requirementEntity = requirementService.deleteRequirement(id);
        return new ResponseEntity<>(requirementEntity, HttpStatus.OK);
    }
}
