package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.ExperienceRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.ExperienceResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentChanelResponse;
import org.example.quanlytuyendung.entity.ExperienceEntity;
import org.example.quanlytuyendung.mapper.ExperienceMapper;
import org.example.quanlytuyendung.repository.ExperienceRepository;
import org.example.quanlytuyendung.service.ExperienceService;
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
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;

    @Override
    public ApiResponse<PageableResponse<ExperienceResponse>> findAll(int page, int size, ExperienceResponse experienceResponse) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Map<String , Object> filter = new HashMap<>();
        if (experienceResponse.getName() != null) {
            filter.put("name", experienceResponse.getName());
        }
        Specification<ExperienceEntity> spec = new BaseSpecification<>(filter);
        var pageData = experienceRepository.findAll(spec, pageable);
        PageableResponse<ExperienceResponse> pageableResponse =  PageableResponse.<ExperienceResponse>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(experienceMapper::toResponse).collect(Collectors.toList()))
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public ExperienceResponse addExperience(ExperienceRequest experienceRequest) {
        ExperienceEntity experienceEntity = experienceMapper.toEntity(experienceRequest);
        experienceRepository.save(experienceEntity);
        return new ExperienceResponse(experienceEntity.getId());
    }

    @Override
    public ExperienceResponse updateExperience(ExperienceRequest experienceRequest) {
        ExperienceEntity experienceEntity = experienceMapper.toEntity(experienceRequest);
        experienceEntity.setId(experienceRequest.getId());
        experienceEntity.setDescription(experienceRequest.getDescription());
        experienceEntity.setIsActive(experienceRequest.getIsActive());
        experienceRepository.save(experienceEntity);
        return new ExperienceResponse(experienceEntity.getId());
    }

    @Override
    public ApiResponse<ExperienceResponse> getExperience(int id) {
        ExperienceEntity experienceEntity = experienceRepository.findById(id).orElse(null);

        return new ApiResponse<>(experienceMapper.toResponse(experienceEntity));
    }

    @Override
    public ExperienceEntity deleteExperience(int id) {
        ExperienceEntity experienceEntity = experienceRepository.findById(id).orElse(null);
        experienceRepository.delete(experienceEntity);
        return null;
    }
}
