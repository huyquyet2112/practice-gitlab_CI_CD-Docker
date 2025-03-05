package org.example.quanlytuyendung.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.JobPositionRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.JobPositionResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;
import org.example.quanlytuyendung.mapper.JobPositionMapper;
import org.example.quanlytuyendung.repository.JobPositionRepository;
import org.example.quanlytuyendung.repository.JobPositiopMapRepository;
import org.example.quanlytuyendung.service.JobpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl implements JobpositionService {
    private final JobPositionRepository jobPositionRepository;
    private final JobPositiopMapRepository jobPositiopMapRepository;
    private final JobPositionMapper jobPositionMapper;

    @Override
    public ApiResponse<PageableResponse<JobPositionResponse>> findAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageData = PageRequest.of(page, size, sort);
        Page<JobPositionEntity> jobPositionEntities = jobPositionRepository.findAll(pageData);

        PageableResponse<JobPositionResponse> pageableResponse = PageableResponse.<JobPositionResponse>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .totalPages(jobPositionEntities.getTotalPages())
                .totalElements(jobPositionEntities.getTotalElements())
                .numberOfElements(jobPositionEntities.getNumberOfElements())
                .content(jobPositionEntities.getContent().stream()
                        .map(jobPositionMapper::toResponseList)
                        .collect(Collectors.toList()))
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public JobPositionResponse addJobPosition(JobPositionRequest request) {
        JobPositionEntity jobPositionEntity = jobPositionMapper.toEntity(request);
        jobPositionRepository.save(jobPositionEntity);

        List<JobPositionEntityMap> maps = new ArrayList<>();
        for (var line : request.getLines()) {
            for (var position : line.getPositions()) {
                JobPositionEntityMap jobPositionMap = new JobPositionEntityMap();
                jobPositionMap.setJobPosition(jobPositionEntity);
                jobPositionMap.setDepartmentId(line.getDepartment().getId());
                jobPositionMap.setPositionId(position.getId());
                maps.add(jobPositionMap);
            }
        }
        jobPositiopMapRepository.saveAll(maps);
        return new JobPositionResponse(jobPositionEntity.getId());
    }

    @Override
    @Transactional
    public JobPositionResponse updatePosition(JobPositionRequest request) {
        JobPositionEntity jobPositionEntity = jobPositionRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Job Position not found!"));

        boolean isDuplicateCode = jobPositionRepository.existsByCode(request.getCode()) &&
                !jobPositionEntity.getCode().equals(request.getCode());
        if (isDuplicateCode) {
            throw new RuntimeException("Code already exists!");
        }

        jobPositionEntity.setIndustry(request.getIndustry());
        jobPositionEntity.setName(request.getName());
        jobPositionEntity.setDescription(request.getDescription());
        jobPositionEntity.setIsActive(request.getIsActive());
        jobPositionEntity.setCode(request.getCode());

        List<JobPositionEntityMap> existingMaps = jobPositiopMapRepository.findByJobPosition(jobPositionEntity);
        Map<Integer, JobPositionEntityMap> existingMapByDept = new HashMap<>();
        for (JobPositionEntityMap map : existingMaps) {
            existingMapByDept.put(map.getDepartmentId(), map);
        }

        List<JobPositionEntityMap> updatedMaps = new ArrayList<>();
        for (var line : request.getLines()) {
            for (var position : line.getPositions()) {
                JobPositionEntityMap jobPositionMap;
                if (existingMapByDept.containsKey(line.getDepartment().getId())) {
                    jobPositionMap = existingMapByDept.get(line.getDepartment().getId());
                    jobPositionMap.setPositionId(position.getId());
                } else {
                    jobPositionMap = new JobPositionEntityMap();
                    jobPositionMap.setJobPosition(jobPositionEntity);
                    jobPositionMap.setDepartmentId(line.getDepartment().getId());
                    jobPositionMap.setPositionId(position.getId());
                }
                updatedMaps.add(jobPositionMap);
            }
        }

        jobPositiopMapRepository.saveAll(updatedMaps);
        jobPositionRepository.save(jobPositionEntity);

        return new JobPositionResponse(jobPositionEntity.getId());
    }

    @Override
    public JobPositionResponse findPosition(int id) {
        JobPositionEntity jobPositionEntity = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Position not found!"));
        List<JobPositionEntityMap> jobPositionEntityMaps = jobPositiopMapRepository.findByJobPosition(jobPositionEntity);
        return jobPositionMapper.toResponseDetails(jobPositionEntity, jobPositionEntityMaps);
    }

    @Override
    public JobPositionEntity deleteJobPosittion(int id) {
        JobPositionEntity jobPositionEntity = jobPositionRepository.findById(id).orElseThrow(() -> new RuntimeException("Job Position not found!"));
        List<JobPositionEntityMap> jobPositionEntityMaps = jobPositiopMapRepository.findByJobPosition(jobPositionEntity);
        jobPositiopMapRepository.deleteAll(jobPositionEntityMaps);
        jobPositionRepository.delete(jobPositionEntity);
        return null;
    }
}