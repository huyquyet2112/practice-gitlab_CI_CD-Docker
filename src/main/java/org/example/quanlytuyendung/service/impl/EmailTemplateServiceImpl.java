package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.EmailTemplateRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.EmailTemplateResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.EmailTemplateEntity;
import org.example.quanlytuyendung.mapper.EmailTemplateMapper;
import org.example.quanlytuyendung.repository.EmailTemplateRepository;
import org.example.quanlytuyendung.service.EmailTemplateService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailTemplateServiceImpl implements EmailTemplateService {
    private final EmailTemplateRepository emailTemplateRepository;
    private final EmailTemplateMapper emailTemplateMapper;

    @Override
    public ApiResponse<PageableResponse<EmailTemplateResponse>> getEmailTemplates(int page, int size,String search,String sort) {
        String [] sortParam = sort.split(":");
        String sortField = sortParam[0];
        Sort.Direction sortDirection = sortParam.length > 1 && sortParam[1].equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort orders = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(page, size, orders);
        Map<String,Object> fileter = new HashMap<>();
         if(search != null && !search.isEmpty()){
          fileter.put("name", search);
         }
        Specification<EmailTemplateEntity> spec = new BaseSpecification<>(fileter);
        var emailTemplateEntities = emailTemplateRepository.findAll(spec,pageable);
        PageableResponse<EmailTemplateResponse> pageableResponse =  PageableResponse.<EmailTemplateResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .totalPages(emailTemplateEntities.getTotalPages())
                .totalElements(emailTemplateEntities.getTotalElements())
                .numberOfElements(emailTemplateEntities.getNumberOfElements())
                .content(emailTemplateEntities.getContent().stream().map(emailTemplateMapper::toResponse).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public ApiResponse<EmailTemplateResponse> createEmailTemplate(EmailTemplateRequest emailTemplateRequest) {
        EmailTemplateEntity emailTemplateEntity = emailTemplateMapper.toEntity(emailTemplateRequest);
         emailTemplateRepository.save(emailTemplateEntity);
        return new ApiResponse<>(new EmailTemplateResponse(emailTemplateEntity.getId()));
    }

    @Override
    public ApiResponse<EmailTemplateResponse> updateEmailTemplate(EmailTemplateRequest emailTemplateRequest) {
        EmailTemplateEntity emailTemplateEntity = emailTemplateRepository.findById(emailTemplateRequest.getId()).orElse(null);
        emailTemplateEntity.setName(emailTemplateRequest.getName());
        emailTemplateEntity.setContent(emailTemplateRequest.getContent());
        emailTemplateEntity.setTitle(emailTemplateRequest.getTitle());

        emailTemplateRepository.save(emailTemplateEntity);
        return new ApiResponse<>(new EmailTemplateResponse(emailTemplateEntity.getId()));
    }

    @Override
    public ApiResponse<EmailTemplateResponse> getEmailTemplate(int id) {
        EmailTemplateEntity emailTemplateEntity = emailTemplateRepository.findById(id).orElse(null);

        return new ApiResponse<>(emailTemplateMapper.toResponse(emailTemplateEntity));
    }

    @Override
    public EmailTemplateEntity deleteEmailTemplate(int id) {
        EmailTemplateEntity emailTemplateEntity = emailTemplateRepository.findById(id).orElse(null);
        emailTemplateRepository.delete(emailTemplateEntity);
        return null;
    }

}
