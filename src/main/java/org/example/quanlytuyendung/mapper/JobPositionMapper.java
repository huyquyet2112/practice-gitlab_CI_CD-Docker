package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.JobPositionRequest;
import org.example.quanlytuyendung.dto.request.LineRequest;
import org.example.quanlytuyendung.dto.response.*;
import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobPositionMapper {
    public static JobPositionResponse toResponseList(JobPositionEntity jobPositionEntity) {
        if (jobPositionEntity == null) {
            return null;
        }

        return new JobPositionResponse(
                null,
                jobPositionEntity.getCode(),
                jobPositionEntity.getName(),
                IndustryMapper.toResponseName(jobPositionEntity.getIndustry()),
                null,
                null,
                jobPositionEntity.getIsActive()
        );
    }

    public static JobPositionEntity toEntity(JobPositionRequest request) {
        JobPositionEntity entity = new JobPositionEntity();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setIndustry(request.getIndustry());
        entity.setDescription(request.getDescription());
        entity.setIsActive(request.getIsActive());
        return entity;
    }
    public static JobPositionResponse toResponseDetails(JobPositionEntity jobPositionEntity, List<JobPositionEntityMap> jobPositionEntityMaps) {
        if (jobPositionEntity == null) {
            return null;
        }


        List<LineResponse> lines = jobPositionEntityMaps.stream()
                .map(JobPositionMapMapper::toResponse)
                .collect(Collectors.toList());

        return new JobPositionResponse(
                null,
                jobPositionEntity.getCode(),
                jobPositionEntity.getName(),
                IndustryMapper.toResponseName(jobPositionEntity.getIndustry()),
                lines,
                null,
                jobPositionEntity.getIsActive()
        );
    }



}

