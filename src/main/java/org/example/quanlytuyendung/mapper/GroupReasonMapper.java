package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.GroupReasonRequest;
import org.example.quanlytuyendung.dto.response.GroupReasonResponse;
import org.example.quanlytuyendung.entity.GroupReasonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupReasonMapper {
    @Mapping(target = "id",ignore = true)
    GroupReasonResponse toResponse(GroupReasonEntity model);
    @Mapping(target = "id",ignore = true)
    GroupReasonEntity toEntity(GroupReasonRequest request);
}
