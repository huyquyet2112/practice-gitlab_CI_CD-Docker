package org.example.quanlytuyendung.service.impl;

import org.example.quanlytuyendung.dto.response.JobPositionResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.example.quanlytuyendung.mapper.IndustryMapper;
import org.example.quanlytuyendung.mapper.JobPositionMapper;
import org.example.quanlytuyendung.repository.JobPositionRepository;
import org.example.quanlytuyendung.service.JobpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class JobPositionServiceImpl  implements JobpositionService {
    private final JobPositionRepository jobPositionRepository;

    public JobPositionServiceImpl(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    @Override
    public PageableResponse<JobPositionResponse> findAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageData = PageRequest.of(page, size, sort);
        Page<JobPositionEntity> jobPositionEntities = jobPositionRepository.findAll(pageData);
        return PageableResponse.<JobPositionEntity>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .totalPages(jobPositionEntities.getTotalPages())
                .totalElements(jobPositionEntities.getTotalElements())
                .numberOfElements(jobPositionEntities.getNumberOfElements())
                .content(jobPositionEntities.getContent().stream()
                        .map(JobPositionMapper::toResponse)
                        .toList())
                .build();
    }
}
