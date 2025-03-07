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
    public ApiResponse<PageableResponse<RecruitmentChanelResponse>> findAllChanel(int page, int size, RecruitmentChanelResponse response) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Map<String , Object> filter = new HashMap<>();
        if (response.getName() != null) filter.put("name", response.getName());

        Specification<RecruitmentChanelEntity> spec = new BaseSpecification<>(filter);
        var pageData = recruitmentChanelRepository.findAll(spec,pageable);
        PageableResponse<RecruitmentChanelResponse> pageableResponse =  PageableResponse.<RecruitmentChanelResponse>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(recruitmentChanelMapper::toResponse).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public RecruitmentChanelResponse addRecruitmentChanel(RecruitmentChanelRequest request) {
        RecruitmentChanelEntity recruitmentChanelEntity = recruitmentChanelMapper.toEntity(request);
        CandicateSourceEntity candicateSourceEntity = candicateSourceRepository.findById(request.getCandicateSource().getId()).orElse(null);
        recruitmentChanelEntity.setCandicateSource(candicateSourceEntity);

        recruitmentChanelRepository.save(recruitmentChanelEntity);
        return new RecruitmentChanelResponse(recruitmentChanelEntity.getId());
    }

    @Override
    public RecruitmentChanelResponse findRecruimentChanel(int id) {
        RecruitmentChanelEntity recruitmentChanelEntity = recruitmentChanelRepository.findById(id).orElse(null);

        return recruitmentChanelMapper.toResponse(recruitmentChanelEntity);
    }

    @Override
    public RecruitmentChanelResponse updateRecruitmentChanel(RecruitmentChanelRequest request) {
        RecruitmentChanelEntity recruitmentChanelEntity = recruitmentChanelRepository.findById(request.getId()).orElse(null);
        recruitmentChanelEntity.setName(request.getName());
        recruitmentChanelEntity.setDescription(request.getDescription());
        recruitmentChanelEntity.setIsActive(request.getIsActive());
        CandicateSourceEntity candicateSourceEntity = candicateSourceRepository.findById(request.getCandicateSource().getId()).orElse(null);
        recruitmentChanelEntity.setCandicateSource(candicateSourceEntity);
        recruitmentChanelRepository.save(recruitmentChanelEntity);
        return new RecruitmentChanelResponse(recruitmentChanelEntity.getId());
    }

    @Override
    public RecruitmentChanelEntity deleteRecruitmentChanel(int id) {
        RecruitmentChanelEntity recruitmentChanelEntity = recruitmentChanelRepository.findById(id).orElse(null);
        recruitmentChanelRepository.delete(recruitmentChanelEntity);
        return null;
    }
}
