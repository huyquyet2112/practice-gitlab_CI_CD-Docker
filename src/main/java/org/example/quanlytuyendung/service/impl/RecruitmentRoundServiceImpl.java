package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.RecruitmentRoundRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentRoundResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentRoundTypeResponse;
import org.example.quanlytuyendung.entity.RecruitmentRoundEntity;
import org.example.quanlytuyendung.entity.RecruitmentRoundTypeEntity;
import org.example.quanlytuyendung.mapper.RecruitmentRoundMapper;
import org.example.quanlytuyendung.repository.RecruitmentRoundRepository;
import org.example.quanlytuyendung.repository.RecruitmentRoundTypeRepository;
import org.example.quanlytuyendung.service.RecruitmentRoundService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentRoundServiceImpl implements RecruitmentRoundService {
    private final RecruitmentRoundRepository recruitmentRoundRepository;
    private final RecruitmentRoundMapper recruitmentRoundMapper;
    private final RecruitmentRoundTypeRepository recruitmentRoundTypeRepository;


    @Override
    public ApiResponse<PageableResponse<RecruitmentRoundResponse>> findRound(int page, int size, RecruitmentRoundResponse recruitmentRoundResponse) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);

        Map<String, Object> filter = new HashMap<>();
        if (recruitmentRoundResponse.getName() != null) filter.put("name", recruitmentRoundResponse.getName());
        if (recruitmentRoundResponse.getCode() != null) filter.put("code", recruitmentRoundResponse.getCode());

        Specification<RecruitmentRoundEntity> specification = new BaseSpecification<>(filter);
        var result = recruitmentRoundRepository.findAll(specification, pageable);

        List<RecruitmentRoundResponse> responseList = result.getContent().stream().map(entity -> {
            RecruitmentRoundResponse response = new RecruitmentRoundResponse();

            response.setName(entity.getName());
            response.setCode(entity.getCode());
            response.setDescription(entity.getDescription());
            response.setIsActive(entity.getIsActive());

            if (entity.getRecruitmentRoundType() != null) {
                RecruitmentRoundTypeResponse roundTypeResponse = new RecruitmentRoundTypeResponse();
                roundTypeResponse.setId(entity.getRecruitmentRoundType().getId());
                roundTypeResponse.setName(entity.getRecruitmentRoundType().getName());
                response.setRecruitmentRoundType(roundTypeResponse.getId(), roundTypeResponse.getName());
            }

            return response;
        }).collect(Collectors.toList());

        PageableResponse<RecruitmentRoundResponse> pageableResponse = PageableResponse.<RecruitmentRoundResponse>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .numberOfElements(result.getNumberOfElements())
                .totalElements(result.getTotalElements())
                .content(responseList)
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public RecruitmentRoundResponse addRecruitmentRound(RecruitmentRoundRequest recruitmentRoundRequest) {
       RecruitmentRoundEntity recruitmentRoundEntity = recruitmentRoundMapper.toEntity(recruitmentRoundRequest);
        RecruitmentRoundTypeEntity recruitmentRoundTypeEntity = recruitmentRoundTypeRepository.findById(recruitmentRoundEntity.getRecruitmentRoundType().getId()).orElse(null);
        recruitmentRoundEntity.setRecruitmentRoundType(recruitmentRoundTypeEntity);
        recruitmentRoundRepository.save(recruitmentRoundEntity);
        return new RecruitmentRoundResponse(recruitmentRoundEntity.getId());
    }

    @Override
    public ApiResponse<RecruitmentRoundResponse> updateRound(RecruitmentRoundRequest recruitmentRoundRequest) {
        RecruitmentRoundEntity resp = recruitmentRoundMapper.toEntity(recruitmentRoundRequest);
        resp.setName(recruitmentRoundRequest.getName());
        resp.setCode(recruitmentRoundRequest.getCode());
        resp.setDescription(recruitmentRoundRequest.getDescription());
        resp.setIsActive(recruitmentRoundRequest.getIsActive());
        RecruitmentRoundTypeEntity recruitmentRoundTypeEntity = recruitmentRoundTypeRepository.findById(recruitmentRoundRequest.getRecruitmentRoundType().getId()).orElse(null);
        resp.setRecruitmentRoundType(recruitmentRoundTypeEntity);
        recruitmentRoundRepository.save(resp);
        return new ApiResponse<>(new RecruitmentRoundResponse(resp.getId()));
    }

    @Override
    public ApiResponse<RecruitmentRoundResponse> getRecruitmentRound(int id) {
        RecruitmentRoundEntity recruitmentRoundEntity = recruitmentRoundRepository.findById(id).get();

        return new ApiResponse<>(recruitmentRoundMapper.toResponse(recruitmentRoundEntity));
    }

    @Override
    public RecruitmentRoundEntity deleteRound(int id) {
        RecruitmentRoundEntity  recruitmentRoundEntity = recruitmentRoundRepository.findById(id).get();
        recruitmentRoundRepository.delete(recruitmentRoundEntity);
        return null;
    }

}
