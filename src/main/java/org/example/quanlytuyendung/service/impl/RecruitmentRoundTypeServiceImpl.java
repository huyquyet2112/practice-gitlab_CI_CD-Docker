package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.RecruitmentRoundTypeRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentRoundTypeResponse;
import org.example.quanlytuyendung.entity.RecruitmentChanelEntity;
import org.example.quanlytuyendung.entity.RecruitmentRoundTypeEntity;
import org.example.quanlytuyendung.mapper.RecruitmentRoundTypeMapper;
import org.example.quanlytuyendung.repository.RecruitmentRoundTypeRepository;
import org.example.quanlytuyendung.service.RecruitmentRoundTypeService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class RecruitmentRoundTypeServiceImpl implements RecruitmentRoundTypeService {
    private final RecruitmentRoundTypeRepository recruitmentRoundTypeRepository;
    private final RecruitmentRoundTypeMapper recruitmentRoundTypeMapper;

    @Override
    public ApiResponse<PageableResponse<RecruitmentRoundTypeResponse>> findAll(int page, int size,String search,String sort) {
        String [] sortParam = sort.split(":");
        String sortField = sortParam[0];
        Sort.Direction sortDirection = sortParam.length >1 && sortParam[1].equalsIgnoreCase("ASC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort orders = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(page, size, orders);
        Map<String , Object> filters = new HashMap<>();
        if (search != null && !search.isEmpty()) {
            filters.put("name", search);
            filters.put("code", sort);
        }
        Specification<RecruitmentRoundTypeEntity> specification = new BaseSpecification<>(filters);
        var result = recruitmentRoundTypeRepository.findAll(specification, pageable);

        PageableResponse<RecruitmentRoundTypeResponse> pageableResponse =  PageableResponse.<RecruitmentRoundTypeResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .numberOfElements(result.getNumberOfElements())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .content(result.getContent().stream().map(recruitmentRoundTypeMapper::toResponse).collect(Collectors.toList()))
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public ApiResponse<RecruitmentRoundTypeResponse> addRoundType(RecruitmentRoundTypeRequest request) {
        RecruitmentRoundTypeEntity recruitmentRoundTypeEntity = recruitmentRoundTypeMapper.toEntity(request);
        recruitmentRoundTypeRepository.save(recruitmentRoundTypeEntity);
        return new ApiResponse<>(new RecruitmentRoundTypeResponse(recruitmentRoundTypeEntity.getId())) ;
    }

    @Override
    public ApiResponse<RecruitmentRoundTypeResponse> updateRoundType(RecruitmentRoundTypeRequest request) {
        RecruitmentRoundTypeEntity recruitmentChanelEntity = recruitmentRoundTypeRepository.findById(request.getId())  .orElseThrow(() -> new RuntimeException("Round type not found!"));
        recruitmentChanelEntity.setName(request.getName());
        recruitmentChanelEntity.setIsActive(request.getIsActive());
        recruitmentChanelEntity.setCode(recruitmentChanelEntity.getCode());
        recruitmentChanelEntity.setDescription(recruitmentChanelEntity.getDescription());
        recruitmentRoundTypeRepository.save(recruitmentChanelEntity);
        return new ApiResponse<>(new RecruitmentRoundTypeResponse(recruitmentChanelEntity.getId())) ;
    }

    @Override
    public ApiResponse<RecruitmentRoundTypeResponse> getRoundType(int id) {
        RecruitmentRoundTypeEntity recruitmentRoundTypeEntity = recruitmentRoundTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Round type not found!"));

        return new ApiResponse<>(recruitmentRoundTypeMapper.toResponse(recruitmentRoundTypeEntity));
    }

    @Override
    public RecruitmentRoundTypeEntity deleteRoundType(int id) {
       RecruitmentRoundTypeEntity recruitmentRoundTypeEntity = recruitmentRoundTypeRepository.findById(id).orElse(null);
       recruitmentRoundTypeRepository.delete(recruitmentRoundTypeEntity);
        return null;
    }
}
