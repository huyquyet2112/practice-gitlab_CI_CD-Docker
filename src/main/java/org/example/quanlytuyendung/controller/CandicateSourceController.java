package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.CandicateSourceRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.CandicateSourceResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.CandicateSourceEntity;
import org.example.quanlytuyendung.service.CandicateSourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/candicate-source")
public class CandicateSourceController {
    private final CandicateSourceService candicateSourceService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<CandicateSourceResponse>>> getCandicateSourceList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "createdAt:DESC") String sort
    ){


    ApiResponse<PageableResponse<CandicateSourceResponse>> apiResponse = candicateSourceService.findAll(page,size,search,sort);
    return ResponseEntity.ok(apiResponse);
}
    @PostMapping
    public ResponseEntity<ApiResponse<CandicateSourceResponse>> createCandicateSource(@RequestBody CandicateSourceRequest candicateSourceRequest){
       ApiResponse<CandicateSourceResponse> candicateSourceResponse = candicateSourceService.addCandicateSource(candicateSourceRequest);
        return ResponseEntity.ok(candicateSourceResponse);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<CandicateSourceResponse>> updateCandicateSource(@RequestBody CandicateSourceRequest candicateSourceRequest){
     ApiResponse  <CandicateSourceResponse> candicateSourceResponse = candicateSourceService.updateCandicateSource(candicateSourceRequest);
        return ResponseEntity.ok(candicateSourceResponse);
    }
    @GetMapping
    public ResponseEntity<CandicateSourceResponse> getCandicateSource(@RequestParam int id){
        CandicateSourceResponse candicateSourceResponse = candicateSourceService.getCandicateSource(id);
        return ResponseEntity.ok(candicateSourceResponse);
    }
    @DeleteMapping
    public ResponseEntity<CandicateSourceEntity> deleteCandicateSource(@RequestParam int id){
        CandicateSourceEntity candicateSourceEntity = candicateSourceService.deleteCandicateSource(id);
        return ResponseEntity.ok(candicateSourceEntity);
    }
}
