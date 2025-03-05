package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.IndustryRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.IndustryResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.mapper.IndustryMapper;
import org.example.quanlytuyendung.entity.IndustryEntity;
import org.example.quanlytuyendung.repository.IndustryRepository;
import org.example.quanlytuyendung.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IndustryServiceImpl implements IndustryService {

    private final IndustryRepository industryRepo;
    private final IndustryMapper industryMapper;




    @Override
    public PageableResponse<IndustryResponse> findAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<IndustryEntity> pageData = industryRepo.findAll(pageable);

        return PageableResponse.<IndustryResponse>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream()
                        .map(industryMapper::toResponse)
                        .toList())
                .build();
    }

    @Override
    public IndustryResponse save(IndustryRequest industryRequest) {
        if (industryRepo.existsByCode(industryRequest.getCode())) {
            throw new IllegalArgumentException("Try again! Code already exists.");
        }
        IndustryEntity industryEntity = industryMapper.toModel(industryRequest);
        industryEntity = industryRepo.save(industryEntity);
        return industryMapper.toResponseId(industryEntity);
    }

    @Override
    public IndustryResponse update(IndustryRequest industryRequest) {
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

        IndustryEntity updatedEntity = industryRepo.save(industryEntity);
        return industryMapper.toResponseId(updatedEntity);
    }

    @Override
    public ApiResponse<IndustryResponse> findIndustry(int id) {
        IndustryEntity industry = industryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Industry with ID " + id + " not found."));
        IndustryResponse response = industryMapper.toResponseDetails(industry);
        return new ApiResponse<>(response);
    }

    @Override
    public IndustryEntity deleteIndustry(int id) {
        IndustryEntity industryEntity = industryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Industry with ID " + id + " not found."));
        industryRepo.delete(industryEntity);
        return industryEntity;
    }
}
