package org.example.quanlytuyendung.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.RecruitmentChanelRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentChanelResponse;
import org.example.quanlytuyendung.entity.CandicateSourceEntity;
import org.example.quanlytuyendung.entity.RecruitmentChanelEntity;
import org.example.quanlytuyendung.mapper.CandicateSourceMapper;
import org.example.quanlytuyendung.mapper.RecruitmentChanelMapper;
import org.example.quanlytuyendung.repository.CandicateSourceRepository;
import org.example.quanlytuyendung.repository.RecruitmentChanelRepository;
import org.example.quanlytuyendung.service.RecruitmentService;
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
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentChanelRepository recruitmentChanelRepository;
    private final RecruitmentChanelMapper recruitmentChanelMapper;
    private final CandicateSourceRepository candicateSourceRepository;



    @Override
    public ApiResponse<PageableResponse<RecruitmentChanelResponse>> findAllChanel(int page, int size,String search,String sort) {
        String [] sortParam = sort.split(":");
        String sortField = sortParam[0];
        Sort.Direction sortDirection = sortParam.length > 1 && sortParam[1].equalsIgnoreCase("ASC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort orders = Sort.by(sortDirection,sortField);
        Pageable pageable = PageRequest.of(page, size, orders);
        Map<String , Object> filter = new HashMap<>();
        if(search != null && !search.isEmpty()){
            filter.put("name",search);
        }

        Specification<RecruitmentChanelEntity> spec = new BaseSpecification<>(filter);
        var pageData = recruitmentChanelRepository.findAll(spec,pageable);
        PageableResponse<RecruitmentChanelResponse> pageableResponse =  PageableResponse.<RecruitmentChanelResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(recruitmentChanelMapper::toResponse).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public ApiResponse<RecruitmentChanelResponse> addRecruitmentChanel(RecruitmentChanelRequest request) {
        RecruitmentChanelEntity recruitmentChanelEntity = recruitmentChanelMapper.toEntity(request);
        CandicateSourceEntity candicateSourceEntity = candicateSourceRepository.findById(request.getCandicateSource().getId()).orElse(null);
        recruitmentChanelEntity.setCandicateSource(candicateSourceEntity);

        recruitmentChanelRepository.save(recruitmentChanelEntity);
        return new ApiResponse<>( new RecruitmentChanelResponse(recruitmentChanelEntity.getId()));
    }

    @Override
    public ApiResponse<RecruitmentChanelResponse> findRecruimentChanel(int id) {
        RecruitmentChanelEntity recruitmentChanelEntity = recruitmentChanelRepository.findById(id).orElse(null);

        return new ApiResponse<>(recruitmentChanelMapper.toResponse(recruitmentChanelEntity)) ;
    }

    @Override
    public ApiResponse<RecruitmentChanelResponse> updateRecruitmentChanel(RecruitmentChanelRequest request) {
        RecruitmentChanelEntity recruitmentChanelEntity = recruitmentChanelRepository.findById(request.getId()).orElse(null);
        recruitmentChanelEntity.setName(request.getName());
        recruitmentChanelEntity.setDescription(request.getDescription());
        recruitmentChanelEntity.setIsActive(request.getIsActive());
        CandicateSourceEntity candicateSourceEntity = candicateSourceRepository.findById(request.getCandicateSource().getId()).orElse(null);
        recruitmentChanelEntity.setCandicateSource(candicateSourceEntity);
        recruitmentChanelRepository.save(recruitmentChanelEntity);
        return new ApiResponse<>( new RecruitmentChanelResponse(recruitmentChanelEntity.getId()));
    }

    @Override
    public RecruitmentChanelEntity deleteRecruitmentChanel(int id) {
        RecruitmentChanelEntity recruitmentChanelEntity = recruitmentChanelRepository.findById(id).orElse(null);
        recruitmentChanelRepository.delete(recruitmentChanelEntity);
        return null;
    }
}
