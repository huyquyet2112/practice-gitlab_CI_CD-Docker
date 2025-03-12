package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.ExperienceRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.ExperienceResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.ExperienceEntity;
import org.example.quanlytuyendung.service.ExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/experience")
public class ExperienceController {
    private final ExperienceService experienceService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<ExperienceResponse>>> getExperience(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "createdAt:DESC") String sort


    ) {

        ApiResponse<PageableResponse<ExperienceResponse>> apiResponse = experienceService.findAll(page,size,search,sort);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse <ExperienceResponse>> addExperience(@RequestBody ExperienceRequest experienceRequest) {
      ApiResponse  <ExperienceResponse> experienceResponse = experienceService.addExperience(experienceRequest);
        return ResponseEntity.ok(experienceResponse);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<ExperienceResponse>> updateExperience(@RequestBody ExperienceRequest experienceRequest) {
        ApiResponse <ExperienceResponse> experienceResponse = experienceService.updateExperience(experienceRequest);
        return ResponseEntity.ok(experienceResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<ExperienceResponse>> getExperience(@RequestParam int id) {
        ApiResponse<ExperienceResponse> responseApiResponse = experienceService.getExperience(id);
        return ResponseEntity.ok(responseApiResponse);
    }
    @DeleteMapping
    public ResponseEntity<ExperienceEntity> deleteExperience(@RequestParam int id) {
        ExperienceEntity experienceEntity = experienceService.deleteExperience(id);
        return ResponseEntity.ok(experienceEntity);
    }
}
