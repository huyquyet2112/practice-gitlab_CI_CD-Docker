package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.JobPositionRequest;
import org.example.quanlytuyendung.dto.response.JobPositionResponse;
import org.example.quanlytuyendung.dto.response.LineResponse;
import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {JobPositionMapMapper.class})
public interface JobPositionMapper {

    JobPositionMapper INSTANCE = Mappers.getMapper(JobPositionMapper.class);

    // Chuyển từ JobPositionEntity sang JobPositionResponse (danh sách)
    @Mapping(target = "industry", ignore = true)
    @Mapping(target = "lines", ignore = true)
    @Mapping(target = "otherField", ignore = true) // Nếu có thêm trường cần bỏ qua
    JobPositionResponse toResponseList(JobPositionEntity jobPositionEntity);

    // Chuyển từ JobPositionRequest sang JobPositionEntity
    JobPositionEntity toEntity(JobPositionRequest request);

    // Chuyển từ JobPositionEntity sang JobPositionResponse (chi tiết)
    @Mapping(target = "industry", ignore = true)
    @Mapping(source = "jobPositionEntityMaps", target = "lines", qualifiedByName = "mapLines")
    JobPositionResponse toResponseDetails(JobPositionEntity jobPositionEntity, List<JobPositionEntityMap> jobPositionEntityMaps);

    // Xử lý danh sách JobPositionEntityMap -> LineResponse
    @Named("mapLines")
    static List<LineResponse> mapLines(List<JobPositionEntityMap> jobPositionEntityMaps) {
        return jobPositionEntityMaps.stream()
                .map(JobPositionMapMapper.INSTANCE::toResponse)
                .collect(Collectors.toList());
    }
}
