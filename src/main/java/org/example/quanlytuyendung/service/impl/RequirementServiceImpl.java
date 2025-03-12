package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.RequirementRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RequirementResponse;
import org.example.quanlytuyendung.entity.RequirementEntity;
import org.example.quanlytuyendung.mapper.RequirementMapper;
import org.example.quanlytuyendung.repository.RequirementRepository;
import org.example.quanlytuyendung.service.RequirementService;
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
public class RequirementServiceImpl implements RequirementService {
    private final RequirementRepository requirementRepository;



    @Override
    public ApiResponse<PageableResponse<RequirementResponse>> findAll(int page, int size,String search ,String sort) {
        String [] sortParam = sort.split(":");
        String sortField = sortParam[0];
        Sort.Direction sortDirection = sortParam.length > 1 && sortParam[1].equalsIgnoreCase("ASC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort orders = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(page, size, orders);
        Map<String, Object> filters = new HashMap<>();
        if (search != null && !search.isEmpty()) {
            filters.put("name", search);
        }
        Specification<RequirementEntity> spec = new BaseSpecification<>(filters);
        var pageData = requirementRepository.findAll(spec, pageable);
        PageableResponse<RequirementResponse> pageableResponse = PageableResponse.<RequirementResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(RequirementMapper::toRequirementResponse).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public ApiResponse<RequirementResponse> addRequirement(RequirementRequest requirementRequest) {
        RequirementEntity requirementEntity = RequirementMapper.toEntity(requirementRequest);
        requirementRepository.save(requirementEntity);
        return new ApiResponse<>( new RequirementResponse(requirementEntity.getId()));
    }

    @Override
    public ApiResponse<RequirementResponse> updateRequirement(RequirementRequest requirementRequest) {
        RequirementEntity requirementEntity = requirementRepository.findById(requirementRequest.getId()).orElse(null);
        requirementEntity.setName(requirementRequest.getName());
        requirementEntity.setDescription(requirementRequest.getDescription());
        requirementEntity.setIsActive(requirementRequest.getIsActive());
        requirementEntity.setDepartment(requirementEntity.getDepartment());
        requirementRepository.save(requirementEntity);
        return new ApiResponse<>( new RequirementResponse(requirementEntity.getId()));
    }

    @Override
    public ApiResponse<RequirementResponse> getReuirement(int id) {
        RequirementEntity requirementEntity = requirementRepository.findById(id).orElse(null);

        return new ApiResponse<>(RequirementMapper.toRequirementResponse(requirementEntity));
    }

    @Override
    public RequirementEntity deleteRequirement(int id) {
        RequirementEntity requirementEntity = requirementRepository.findById(id).orElse(null);
        requirementRepository.delete(requirementEntity);
        return null;
    }
}
