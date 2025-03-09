package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.EmailTemplateRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.EmailTemplateResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.EmailTemplateEntity;
import org.example.quanlytuyendung.service.EmailTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/vi/email-template")
public class EmailTemplateController {
    private final EmailTemplateService emailTemplateService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<EmailTemplateResponse>>> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name
    ){
        EmailTemplateResponse emailTemplateResponse = new EmailTemplateResponse();
        emailTemplateResponse.setName(name);
        ApiResponse<PageableResponse<EmailTemplateResponse>> apiResponse = emailTemplateService.getEmailTemplates(page,size,emailTemplateResponse);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<EmailTemplateResponse>> createEmailTemplate(@RequestBody EmailTemplateRequest emailTemplateRequest) {
        ApiResponse<EmailTemplateResponse> responseApiResponse = emailTemplateService.createEmailTemplate(emailTemplateRequest);
        return ResponseEntity.ok(responseApiResponse);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<EmailTemplateResponse>> updateEmailTemplate(@RequestBody EmailTemplateRequest emailTemplateRequest) {
        ApiResponse<EmailTemplateResponse> responseApiResponse = emailTemplateService.updateEmailTemplate(emailTemplateRequest);
        return ResponseEntity.ok(responseApiResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<EmailTemplateResponse>> getEmailTemplate(@RequestParam int id) {
        ApiResponse<EmailTemplateResponse> emailTemplateResponseApiResponse = emailTemplateService.getEmailTemplate(id);
        return ResponseEntity.ok(emailTemplateResponseApiResponse);
    }
    @DeleteMapping
    public ResponseEntity<EmailTemplateEntity> deleteEmailTemplate(@RequestParam int id) {
        EmailTemplateEntity emailTemplateEntity = emailTemplateService.deleteEmailTemplate(id);
        return ResponseEntity.ok(emailTemplateEntity);
    }
}
