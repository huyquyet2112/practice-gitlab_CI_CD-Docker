package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.WorkTypeRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.WorkTypeResponse;
import org.example.quanlytuyendung.entity.WorkTypeEntity;
import org.example.quanlytuyendung.service.WorkTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/work-type")
@RequiredArgsConstructor
public class WorkTypeController {
    private final WorkTypeService workTypeService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<WorkTypeResponse>>> getWorkTypeList(
            @RequestParam(defaultValue = "0" , required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam (value = "code",required = false)String code
    ){
        WorkTypeResponse workTypeResponse = new WorkTypeResponse();
        workTypeResponse.setName(name);
        workTypeResponse.setCode(code);
        ApiResponse<PageableResponse<WorkTypeResponse>> apiResponse = workTypeService.getWorkTypeList(page,size,workTypeResponse);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<WorkTypeResponse>> createWorkType(@RequestBody WorkTypeRequest workTypeRequest) {
        ApiResponse<WorkTypeResponse> workTypeResponseApiResponse = workTypeService.addWorkType(workTypeRequest);
        return ResponseEntity.ok(workTypeResponseApiResponse);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<WorkTypeResponse>> updateWorkType(@RequestBody WorkTypeRequest workTypeRequest) {
        ApiResponse<WorkTypeResponse> workTypeResponseApiResponse = workTypeService.updateWorkType(workTypeRequest);
        return ResponseEntity.ok(workTypeResponseApiResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<WorkTypeResponse>> getWorkType(@RequestParam int id) {
        ApiResponse<WorkTypeResponse> workTypeResponseApiResponse = workTypeService.getWorkType(id);
        return ResponseEntity.ok(workTypeResponseApiResponse);
    }
    @DeleteMapping
    public ResponseEntity<WorkTypeEntity> deleteWorkType(@RequestParam int id) {
        WorkTypeEntity workType = workTypeService.deleteWorkType(id);
        return ResponseEntity.ok(workType);
    }
}
