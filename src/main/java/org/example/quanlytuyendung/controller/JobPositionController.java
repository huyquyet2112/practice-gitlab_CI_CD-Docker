package org.example.quanlytuyendung.controller;

import org.example.quanlytuyendung.dto.response.JobPositionResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.service.JobpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/job-position")
public class JobPositionController {

    private final JobpositionService jobpositionService;
    @Autowired
    public JobPositionController(JobpositionService jobpositionService) {
        this.jobpositionService = jobpositionService;
    }
    @GetMapping("/list")
    public ResponseEntity<PageableResponse<JobPositionResponse>> getAllJobPosition(
            @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size) {
            PageableResponse<JobPositionResponse> jobPositionResponse = jobpositionService.findAll(page,size);
            return new ResponseEntity<>(jobPositionResponse, HttpStatus.OK);

    }
}
