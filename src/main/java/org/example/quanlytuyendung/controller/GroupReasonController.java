package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.GroupReasonRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.GroupReasonResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.GroupReasonEntity;
import org.example.quanlytuyendung.service.GroupReasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group-reason")
public class GroupReasonController {
    private final GroupReasonService groupReasonService;


    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<GroupReasonResponse>>> getAllGroupReason(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "createdAt:DESC") String sort) {

        ApiResponse<PageableResponse<GroupReasonResponse>> pageableResponseApiResponse = groupReasonService.findAll(page,size,search,sort);
        return new ResponseEntity<>(pageableResponseApiResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<GroupReasonResponse>> addGroupReason(@RequestBody GroupReasonRequest groupReasonRequest) {
       ApiResponse <GroupReasonResponse> groupReasonResponse = groupReasonService.addGroupReason(groupReasonRequest);
        return new ResponseEntity<>(groupReasonResponse, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ApiResponse <GroupReasonResponse>> updateGroupReason(@RequestBody GroupReasonRequest groupReasonRequest) {
      ApiResponse  <GroupReasonResponse> groupReasonResponse = groupReasonService.updateGroupReason(groupReasonRequest);
        return new ResponseEntity<>(groupReasonResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<GroupReasonResponse>> detailsGroupReason(@RequestParam int id) {
        ApiResponse<GroupReasonResponse> groupReasonResponseApiResponse = groupReasonService.detailsGroupReason(id);
        return new ResponseEntity<>(groupReasonResponseApiResponse, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<GroupReasonEntity> deleteGroupReason(@RequestParam int id) {
        GroupReasonEntity groupReasonEntity = groupReasonService.deleteGroupReason(id);
        return new ResponseEntity<>(groupReasonEntity, HttpStatus.OK);
    }
}
