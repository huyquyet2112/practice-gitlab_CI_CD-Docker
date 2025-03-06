package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.RecruitmentChanelRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.ReasonResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentChanelResponse;
import org.example.quanlytuyendung.entity.CandicateSourceEntity;
import org.example.quanlytuyendung.entity.RecruitmentChanelEntity;
import org.example.quanlytuyendung.mapper.CandicateSourceMapper;
import org.example.quanlytuyendung.mapper.RecruitmentChanelMapper;
import org.example.quanlytuyendung.repository.RecruitmentChanelRepository;
import org.example.quanlytuyendung.service.RecruitmentService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentChanelRepository recruitmentChanelRepository;
    private final RecruitmentChanelMapper recruitmentChanelMapper;
    private final CandicateSourceMapper candicateSourceMapper;



    @Override
    public ApiResponse<PageableResponse<RecruitmentChanelResponse>> findAllChanel(int page, int size, RecruitmentChanelResponse response) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Map<String , Object> filter = new HashMap<>();
        filter.put("name", response.getName());
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
        recruitmentChanelRepository.save(recruitmentChanelEntity);
        return new RecruitmentChanelResponse(recruitmentChanelEntity.getId());
    }
}
