package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.response.DepartmentResponse;
import org.example.quanlytuyendung.dto.response.JobPositionResponse;
import org.example.quanlytuyendung.dto.response.LineResponse;
import org.example.quanlytuyendung.dto.response.PositionResponse;
import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JobPositionMapMapper {
    public static LineResponse toResponse(JobPositionEntityMap jobPositionEntityMap) {
        DepartmentResponse departmentResponse = new DepartmentResponse(jobPositionEntityMap.getDepartmentId());

        List<PositionResponse> positions = new ArrayList<>();
        positions.add(new PositionResponse(jobPositionEntityMap.getPositionId()));
        return new LineResponse(departmentResponse, positions);
    }

}

