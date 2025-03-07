package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.ExperienceRequest;
import org.example.quanlytuyendung.dto.response.ExperienceResponse;
import org.example.quanlytuyendung.entity.ExperienceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {
    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);
    @Mapping(target = "id", ignore = true)
    ExperienceResponse toResponse(ExperienceEntity experienceEntity);
    @Mapping(target = "id", ignore = true)
    ExperienceEntity toEntity(ExperienceRequest experienceRequest);
}
