package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.JobPositionRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.JobPositionResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.example.quanlytuyendung.service.JobpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-position")
@RequiredArgsConstructor
public class JobPositionController {

    private final JobpositionService jobpositionService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<JobPositionResponse>>> getAllJobPosition(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        ApiResponse<PageableResponse<JobPositionResponse>> response = jobpositionService.findAll(page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<JobPositionResponse>> createJobPosition(@RequestBody JobPositionRequest request) {
        JobPositionResponse jobPositionResponse = jobpositionService.addJobPosition(request);
        ApiResponse<JobPositionResponse> response = new ApiResponse<>(jobPositionResponse);
        return ResponseEntity.ok(response);
    }
    @PutMapping()
    public ResponseEntity<ApiResponse<JobPositionResponse>> updateJobPosition(@RequestBody JobPositionRequest request) {
        JobPositionResponse jobPositionResponse = jobpositionService.updatePosition(request);
        ApiResponse<JobPositionResponse> response = new ApiResponse<>(jobPositionResponse);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<JobPositionResponse> getJobPosition(@RequestParam int id) {
        JobPositionResponse jobPositionResponse = jobpositionService.findPosition(id);
        return ResponseEntity.ok(jobPositionResponse);
    }
    @DeleteMapping
    public ResponseEntity<JobPositionEntity> deleteJobPosition(@RequestParam int id) {
        JobPositionEntity jobPositionEntity = jobpositionService.deleteJobPosittion(id);
        return ResponseEntity.ok(jobPositionEntity);
    }


}
