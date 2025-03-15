package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.JobPositionRequest;
import org.example.quanlytuyendung.dto.response.JobPositionResponse;
import org.example.quanlytuyendung.dto.response.LineResponse;
import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {IndustryMapper.class, JobPositionMapMapper.class})
public interface JobPositionMapper {
    @Mapping(target = "industry", source = "jobPositionEntity.industry", qualifiedByName = "toResponseName")
    @Mapping(target = "lines", source = "lines") // ✅ Chỉnh sửa: sử dụng danh sách LineResponse
    JobPositionResponse toResponseDetails(JobPositionEntity jobPositionEntity, List<LineResponse> lines);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "industry", source = "jobPositionEntity.industry", qualifiedByName = "toResponseName")
    JobPositionResponse toResponseList(JobPositionEntity jobPositionEntity);

    JobPositionEntity toEntity(JobPositionRequest request);
}



