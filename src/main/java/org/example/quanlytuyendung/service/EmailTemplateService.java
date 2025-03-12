package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.EmailTemplateRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.EmailTemplateResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.EmailTemplateEntity;

public interface EmailTemplateService {
    ApiResponse<PageableResponse<EmailTemplateResponse>> getEmailTemplates(int page, int size,String search,String sort);

    ApiResponse<EmailTemplateResponse> createEmailTemplate(EmailTemplateRequest emailTemplateRequest);

    ApiResponse<EmailTemplateResponse> updateEmailTemplate(EmailTemplateRequest emailTemplateRequest);

    ApiResponse<EmailTemplateResponse> getEmailTemplate(int id);

    EmailTemplateEntity deleteEmailTemplate(int id);
}
