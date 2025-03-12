package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.ReasonRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.ReasonResponse;
import org.example.quanlytuyendung.entity.ReasonEntity;
import org.example.quanlytuyendung.entity.TagEntity;
import org.example.quanlytuyendung.service.ReasonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reason")
public class ReasonController {
    private final ReasonService reasonService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<ReasonResponse>>> getAllReasons(@RequestParam(defaultValue = "0") int page,
                                                                                       @RequestParam(defaultValue = "10") int size,
                                                                                       @RequestParam(required = false) String search,
                                                                                       @RequestParam(defaultValue = "createdAt:DESC") String sort){

      ApiResponse<PageableResponse<ReasonResponse>> apiResponse = reasonService.findAllReason(page,size,search,sort);
      return ResponseEntity.ok(apiResponse);

    }
    @PostMapping
    public ResponseEntity<ApiResponse<ReasonResponse>> addReason(@RequestBody ReasonRequest reasonRequest) {
       ApiResponse <ReasonResponse> reasonResponse = reasonService.addReason(reasonRequest);
        return ResponseEntity.ok(reasonResponse);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<ReasonResponse>> updateReason(@RequestBody ReasonRequest reasonRequest) {
        ApiResponse <ReasonResponse> reasonResponse = reasonService.updateReason(reasonRequest);
        return ResponseEntity.ok(reasonResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<ReasonResponse>> getReasonById(@RequestParam Integer id) {
        ApiResponse <ReasonResponse> reasonResponse = reasonService.findReason(id);
        return ResponseEntity.ok(reasonResponse);
    }
    @DeleteMapping
    public ResponseEntity<ReasonEntity> deleteReason(@RequestParam Integer id){
        ReasonEntity reasonEntity = reasonService.deleteReason(id);
        return ResponseEntity.ok(reasonEntity);
    }
}
