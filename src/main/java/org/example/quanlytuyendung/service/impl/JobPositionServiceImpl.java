package org.example.quanlytuyendung.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.JobPositionRequest;
import org.example.quanlytuyendung.dto.request.PositionRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.JobPositionResponse;
import org.example.quanlytuyendung.dto.response.LineResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;
import org.example.quanlytuyendung.mapper.IndustryMapper;
import org.example.quanlytuyendung.mapper.JobPositionMapper;
import org.example.quanlytuyendung.repository.JobPositionRepository;
import org.example.quanlytuyendung.repository.JobPositiopMapRepository;
import org.example.quanlytuyendung.service.JobpositionService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl  implements JobpositionService {
    private final JobPositionRepository jobPositionRepository;
    private final JobPositiopMapRepository jobPositiopMapRepository;
    private final JobPositionMapper jobPositionMapper;


    @Override
    public ApiResponse<PageableResponse<JobPositionResponse>> findAll(int page, int size,String search,String sort) {
        String [] sortParam = sort.split(":");
        String sortField = sortParam[0];
        Sort.Direction sortDirection = sortParam.length > 1 && sortParam[1].equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort orders = Sort.by(sortDirection, sortField);
        Pageable pageData = PageRequest.of(page, size, orders);
        Map<String,Object> filters = new HashMap<>();
      if (search != null && !search.isEmpty()) {
          filters.put("name", search);
          filters.put("code", search);
      }
        Specification<JobPositionEntity> spec = new BaseSpecification<>(filters);

       var jobPositionEntities = jobPositionRepository.findAll(spec,pageData);

        PageableResponse<JobPositionResponse> pageableResponse = PageableResponse.<JobPositionResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
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
    public ApiResponse <JobPositionResponse> addJobPosition(JobPositionRequest request) {
        JobPositionEntity jobPositionEntity = jobPositionMapper.toEntity(request);
        jobPositionRepository.save(jobPositionEntity);

       Map<Integer,List<Integer>> departPosit = new HashMap<>();
       for(var line : request.getLines()){
           Integer departmentId = line.getDepartment().getId();
           departPosit.put(departmentId,new ArrayList<>());
           for(var position : line.getPositions()){
              departPosit.get(departmentId).add(position.getId());
           }
       }
       List<JobPositionEntityMap> jobPositionEntityMaps = new ArrayList<>();
       for(var entry : departPosit.entrySet()){
           Integer departmentId = entry.getKey();
           List<Integer> positions = entry.getValue();
           for(var position : positions){
               JobPositionEntityMap jobPositionEntityMap = new JobPositionEntityMap();
               jobPositionEntityMap.setDepartmentId(departmentId);
               jobPositionEntityMap.setJobPosition(jobPositionEntity);
               jobPositionEntityMap.setPositionId(position);
               jobPositionEntityMaps.add(jobPositionEntityMap);

           }
       }
       jobPositiopMapRepository.saveAll(jobPositionEntityMaps);
return new ApiResponse<>( new JobPositionResponse(jobPositionEntity.getId()));
    }




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


        Map<String, JobPositionEntityMap> existingMapByDeptPos = new HashMap<>();
        for (JobPositionEntityMap map : existingMaps) {
            String key = map.getDepartmentId() + "-" + map.getPositionId();
            existingMapByDeptPos.put(key, map);
        }

        List<JobPositionEntityMap> updatedMaps = new ArrayList<>();

        for (var line : request.getLines()) {
            for (var position : line.getPositions()) {
                String key = line.getDepartment().getId() + "-" + position.getId();
                JobPositionEntityMap jobPositionMap;

                if (existingMapByDeptPos.containsKey(key)) {
                    jobPositionMap = existingMapByDeptPos.get(key);
                } else {
                    jobPositionMap = new JobPositionEntityMap();
                    jobPositionMap.setJobPosition(jobPositionEntity);
                    jobPositionMap.setDepartmentId(line.getDepartment().getId());
                    jobPositionMap.setPositionId(position.getId());
                }

                updatedMaps.add(jobPositionMap);
            }
        }


        existingMaps.removeAll(updatedMaps);
        jobPositiopMapRepository.deleteAll(existingMaps);

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
