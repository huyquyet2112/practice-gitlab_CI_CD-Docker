package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.RequirementRequest;
import org.example.quanlytuyendung.dto.response.DepartmentResponse;
import org.example.quanlytuyendung.dto.response.RequirementResponse;
import org.example.quanlytuyendung.entity.RequirementEntity;
import org.springframework.stereotype.Component;

@Component
public class RequirementMapper {

    public static RequirementResponse toRequirementResponse(RequirementEntity requirementEntity) {
        if (requirementEntity == null) {
            return null;
        }
        return new RequirementResponse(
                null,
                requirementEntity.getName(),
                requirementEntity.getDescription(),
                requirementEntity.getIsActive(),
                RequirementMapper.toDepartmentResponse(requirementEntity.getDepartment())
        );
    }

    public static DepartmentResponse toDepartmentResponse(Integer departmentId) {
        if (departmentId == null) {
            return null;
        }
        return new DepartmentResponse(departmentId);
    }
    public static RequirementEntity toEntity(RequirementRequest requirementRequest) {
        if (requirementRequest == null) {
            return null;
        }
        return new RequirementEntity(
                null,
                requirementRequest.getName(),
                requirementRequest.getDescription(),
                requirementRequest.getIsActive(),
                requirementRequest.getDepartment().getId()
        );
    }


}


