package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.GroupReasonResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.service.GroupReasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group-reason")
public class GroupReasonController {
    private final GroupReasonService groupReasonService;


    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<GroupReasonResponse>>> getAllGroupReason(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<PageableResponse<GroupReasonResponse>> pageableResponseApiResponse = groupReasonService.findAll(page,size);
        return new ResponseEntity<>(pageableResponseApiResponse, HttpStatus.OK);

    }
}
