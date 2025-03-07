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
    public ApiResponse<PageableResponse<RequirementResponse>> findAll(int page, int size, RequirementResponse requirementResponse) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Map<String, Object> filters = new HashMap<>();
        Specification<RequirementEntity> spec = new BaseSpecification<>(filters);
        var pageData = requirementRepository.findAll(spec, pageable);
        PageableResponse<RequirementResponse> pageableResponse = PageableResponse.<RequirementResponse>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(RequirementMapper::toRequirementResponse).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public RequirementResponse addRequirement(RequirementRequest requirementRequest) {
        RequirementEntity requirementEntity = RequirementMapper.toEntity(requirementRequest);
        requirementRepository.save(requirementEntity);
        return new RequirementResponse(requirementEntity.getId());
    }

    @Override
    public RequirementResponse updateRequirement(RequirementRequest requirementRequest) {
        RequirementEntity requirementEntity = requirementRepository.findById(requirementRequest.getId()).orElse(null);
        requirementEntity.setName(requirementRequest.getName());
        requirementEntity.setDescription(requirementRequest.getDescription());
        requirementEntity.setIsActive(requirementRequest.getIsActive());
        requirementEntity.setDepartment(requirementEntity.getDepartment());
        requirementRepository.save(requirementEntity);
        return new RequirementResponse(requirementEntity.getId());
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
