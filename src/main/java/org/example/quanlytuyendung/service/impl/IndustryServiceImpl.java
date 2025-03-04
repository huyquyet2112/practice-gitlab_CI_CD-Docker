package org.example.quanlytuyendung.service.impl;

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

import java.util.List;

@Service
public class IndustryServiceImpl implements IndustryService {

    private final IndustryRepository industryRepo;
    @Autowired
    public IndustryServiceImpl(IndustryRepository industryRepo) {
        this.industryRepo = industryRepo;
    }


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
                        .map(IndustryMapper::toResponse)
                        .toList())
                .build();
    }




    @Override
    public IndustryResponse save(IndustryRequest industryRequest) {
        if (industryRepo.existsByCode(industryRequest.getCode())) {
            throw new IllegalArgumentException("Try again! Code exists!");
        }
        IndustryEntity industryEntity = IndustryMapper.toModel(industryRequest);
        industryEntity = industryRepo.save(industryEntity);
        return IndustryMapper.toResponseId(industryEntity);
    }

    @Override
    public IndustryResponse update(IndustryRequest industryRequest) {

        IndustryEntity industryEntity = industryRepo.findById(industryRequest.getId())
                .orElseThrow(() -> new RuntimeException("Id doesn't exist!"));

        boolean isDuplicateCode = industryRepo.existsByCode(industryRequest.getCode()) &&
                !industryEntity.getCode().equals(industryRequest.getCode());
        if (isDuplicateCode) {
            throw new RuntimeException("Id doesn't exist!");
        }

        industryEntity.setName(industryRequest.getName());
        industryEntity.setDescription(industryRequest.getDescription());
        industryEntity.setIsActive(industryRequest.getIsActive());

        if (!industryEntity.getCode().equals(industryRequest.getCode())) {
            industryEntity.setCode(industryRequest.getCode());
        }

        IndustryEntity updatedEntity = industryRepo.save(industryEntity);
        return IndustryMapper.toResponseId(updatedEntity);
    }

    @Override
    public ApiResponse<IndustryResponse> findIndustry(int id) {
        IndustryEntity industry = industryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Industry not found!"));
        IndustryResponse response = IndustryMapper.toResponseDetails(industry);
        return new ApiResponse<>(response);
    }



    @Override
    public IndustryEntity deleteIndustry(int id) {
        IndustryEntity industryEntity = industryRepo.getReferenceById(id);
        industryRepo.delete(industryEntity);

        return null;
    }


}
