package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.BenifitRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.BenifitResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.BenifitEntity;
import org.example.quanlytuyendung.service.BenifitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/benifit")
public class BenifitController {
    private final BenifitService benifitService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<BenifitResponse>>> getBenifit(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "createdAt:DESC") String sort

    ) {

        ApiResponse<PageableResponse<BenifitResponse>> apiResponse = benifitService.getBenifit(page,size,search,sort);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<BenifitResponse>> postBenifit(@RequestBody BenifitRequest benifitRequest) {
        ApiResponse<BenifitResponse> benifitResponse = benifitService.postBenifit(benifitRequest);
        return ResponseEntity.ok(benifitResponse);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<BenifitResponse>> updateBenifit(@RequestBody BenifitRequest benifitRequest) {
        ApiResponse<BenifitResponse> benifitResponse = benifitService.updateBenifit(benifitRequest);
        return ResponseEntity.ok(benifitResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<BenifitResponse>> getBenifitId(@RequestParam int id) {
        ApiResponse<BenifitResponse> benifitResponseApiResponse = benifitService.getBenifitId(id);
        return ResponseEntity.ok(benifitResponseApiResponse);
    }
    @DeleteMapping
    public ResponseEntity<BenifitEntity> deleteBenifit(@RequestParam int id) {
        BenifitEntity benifit   = benifitService.deleteBenifit(id);
        return ResponseEntity.ok(benifit);
    }
}
