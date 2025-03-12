package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.IndustryRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.IndustryResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.IndustryEntity;
import org.example.quanlytuyendung.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/industry")
public class IndustryController {
    private final IndustryService industryService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse <PageableResponse<IndustryResponse>>> getAllIndustries(
            @RequestParam(defaultValue = "0" , required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(required = false)String search,
            @RequestParam (defaultValue = "createdAt:DESC")String sort
    ) {

       ApiResponse<PageableResponse<IndustryResponse>> industryPage = industryService.findAll(page, size,search,sort);
        return   new ResponseEntity<>(industryPage, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<ApiResponse <IndustryResponse>> add(@RequestBody IndustryRequest industryRequest) {
       ApiResponse <IndustryResponse> industryResponse = industryService.save(industryRequest);
        return ResponseEntity.ok(industryResponse);
    }
    @PutMapping()
    public ResponseEntity<ApiResponse <IndustryResponse>> update(@RequestBody IndustryRequest industryRequest) {
        ApiResponse <IndustryResponse> industryResponse = industryService.update(industryRequest);
        return ResponseEntity.ok(industryResponse);
    }
    @GetMapping()
    public ResponseEntity<ApiResponse<IndustryResponse>> getById(@RequestParam int id) {
        ApiResponse<IndustryResponse> industryResponse = industryService.findIndustry(id);
        return ResponseEntity.ok((industryResponse));
    }
    @DeleteMapping()
    public ResponseEntity<IndustryEntity> delete(@RequestParam int id) {
        IndustryEntity industryEntity = industryService.deleteIndustry(id);
        return ResponseEntity.ok(industryEntity);
    }

}
