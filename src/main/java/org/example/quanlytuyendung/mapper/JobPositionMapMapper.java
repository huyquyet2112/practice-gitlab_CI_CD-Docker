package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.response.DepartmentResponse;
import org.example.quanlytuyendung.dto.response.LineResponse;
import org.example.quanlytuyendung.dto.response.PositionResponse;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;

@Mapper(componentModel = "spring")
public interface JobPositionMapMapper {

    JobPositionMapMapper INSTANCE = Mappers.getMapper(JobPositionMapMapper.class);

    @Mapping(target = "departmentResponse", expression = "java(new DepartmentResponse(jobPositionEntityMap.getDepartmentId()))")
    @Mapping(target = "positions", expression = "java(Collections.singletonList(new PositionResponse(jobPositionEntityMap.getPositionId())))")
    LineResponse toResponse(JobPositionEntityMap jobPositionEntityMap);
}
