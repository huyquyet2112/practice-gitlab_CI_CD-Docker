package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.response.DepartmentResponse;
import org.example.quanlytuyendung.dto.response.LineResponse;
import org.example.quanlytuyendung.dto.response.PositionResponse;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;
import org.mapstruct.*;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface JobPositionMapMapper {
    @Mapping(target = "department", source = "departmentId", qualifiedByName = "mapDepartment")
    @Mapping(target = "positions", source = "positionId", qualifiedByName = "mapPositions")
    LineResponse toResponse(JobPositionEntityMap jobPositionEntityMap);

    @Named("mapDepartment")
    static DepartmentResponse mapDepartment(int departmentId) {
        return new DepartmentResponse(departmentId);
    }

    @Named("mapPositions")
    static List<PositionResponse> mapPositions(int positionId) {
        return Collections.singletonList(new PositionResponse(positionId));
    }
}
