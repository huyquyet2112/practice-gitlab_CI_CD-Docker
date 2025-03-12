package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.quanlytuyendung.dto.request.IndustryRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.IndustryResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.mapper.IndustryMapper;
import org.example.quanlytuyendung.entity.IndustryEntity;
import org.example.quanlytuyendung.repository.IndustryRepository;
import org.example.quanlytuyendung.service.IndustryService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class IndustryServiceImpl implements IndustryService {

    private final IndustryRepository industryRepo;
    private final IndustryMapper industryMapper;




    @Override
    public ApiResponse<PageableResponse<IndustryResponse>> findAll(int page, int size,String search,String sort) {
        String [] sortParam = sort.split(":");
        String sortField = sortParam[0];
        Sort.Direction sortDirection = sortParam.length > 1 && sortParam[1].equalsIgnoreCase("ASC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort orders = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(page, size, orders);
        Map<String, Object> filters = new HashMap<>();
        if(search != null && !search.isEmpty()){
            filters.put("name", search);
            filters.put("code", search);
        }
        Specification<IndustryEntity> spec = new BaseSpecification<>(filters);
        var pageData = industryRepo.findAll(spec, pageable);

        PageableResponse<IndustryResponse> pageableResponse = PageableResponse.<IndustryResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream()
                        .map(industryMapper::toResponse)
                        .toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }


    @Override
    public ApiResponse <IndustryResponse> save(IndustryRequest industryRequest) {
        if (industryRepo.existsByCode(industryRequest.getCode())) {
            throw new IllegalArgumentException("Try again! Code already exists.");
        }
        IndustryEntity industryEntity = industryMapper.toModel(industryRequest);
        industryEntity = industryRepo.save(industryEntity);
        return new ApiResponse<>(new  IndustryResponse(industryEntity.getId())) ;
    }

    @Override
    public ApiResponse <IndustryResponse> update(IndustryRequest industryRequest) {
        IndustryEntity industryEntity = industryRepo.findById(industryRequest.getId())
                .orElseThrow(() -> new RuntimeException("Industry with ID " + industryRequest.getId() + " doesn't exist."));

        boolean isDuplicateCode = industryRepo.existsByCode(industryRequest.getCode()) &&
                !industryEntity.getCode().equals(industryRequest.getCode());
        if (isDuplicateCode) {
            throw new RuntimeException("Code '" + industryRequest.getCode() + "' already exists.");
        }

        industryEntity.setName(industryRequest.getName());
        industryEntity.setDescription(industryRequest.getDescription());
        industryEntity.setIsActive(industryRequest.getIsActive());

        if (!industryEntity.getCode().equals(industryRequest.getCode())) {
            industryEntity.setCode(industryRequest.getCode());
        }
         industryRepo.save(industryEntity);
        return new ApiResponse<>(new  IndustryResponse(industryEntity.getId())) ;
    }

    @Override
    public ApiResponse<IndustryResponse> findIndustry(int id) {
        IndustryEntity industry = industryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Industry with ID " + id + " not found."));
        IndustryResponse response = industryMapper.toResponse(industry);
        return new ApiResponse<>(response);
    }

    @Override
    public IndustryEntity deleteIndustry(int id) {
        IndustryEntity industryEntity = industryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Industry with ID " + id + " not found."));
        industryRepo.delete(industryEntity);
        return null;
    }
}
