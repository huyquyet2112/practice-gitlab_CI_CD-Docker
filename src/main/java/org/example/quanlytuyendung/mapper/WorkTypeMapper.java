package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.WorkTypeRequest;
import org.example.quanlytuyendung.dto.response.WorkTypeResponse;
import org.example.quanlytuyendung.entity.WorkTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkTypeMapper {
    @Mapping(target = "id",ignore = true)
    WorkTypeResponse toResponse(WorkTypeEntity workTypeEntity);
    @Mapping(target = "id",ignore = true)
    WorkTypeEntity toEntity(WorkTypeRequest workTypeRequest);
}
