package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.response.DepartmentResponse;
import org.example.quanlytuyendung.dto.response.LineResponse;
import org.example.quanlytuyendung.dto.response.PositionResponse;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;
import org.mapstruct.*;

import java.util.*;

@Mapper(componentModel = "spring")
public interface JobPositionMapMapper {


    @Named("mapDepartment")
    static DepartmentResponse mapDepartment(int departmentId) {
        return new DepartmentResponse(departmentId);
    }

    @Named("mapPositions")
    static List<PositionResponse> mapPositions(int positionId) {
        return Collections.singletonList(new PositionResponse(positionId));
    }
    @Named("toLineResponses")
    default List<LineResponse> toLineResponses(List<JobPositionEntityMap> jobPositionEntityMaps) {
        Map<Integer, LineResponse> departmentMap = new HashMap<>();

        for (JobPositionEntityMap entityMap : jobPositionEntityMaps) {
            Integer departmentId = entityMap.getDepartmentId();
            Integer positionId = entityMap.getPositionId();

            departmentMap.putIfAbsent(departmentId, new LineResponse(new DepartmentResponse(departmentId, null), new ArrayList<>()));


            departmentMap.get(departmentId).getPositions().add(new PositionResponse(positionId));
        }

        return new ArrayList<>(departmentMap.values());
    }
}
