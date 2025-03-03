package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.response.IndustryResponse;
import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.springframework.stereotype.Component;

@Component
public class JobPositionMapper {
    public static IndustryResponse toResponse(JobPositionEntity jobPositionEntity) {

       return new IndustryResponse(
               null,
               jobPositionEntity.getCode(),
               jobPositionEntity.getName(),
               jobPositionEntity.getIndustry(IndustryMapper.toResponseName(jobPositionEntity.getIndustry())),

               jobPositionEntity.getIsActive()


       );
    }
}
