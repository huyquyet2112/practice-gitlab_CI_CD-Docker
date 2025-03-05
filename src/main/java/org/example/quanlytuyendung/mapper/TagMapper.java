package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.TagRequest;
import org.example.quanlytuyendung.dto.response.TagResponse;
import org.example.quanlytuyendung.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    @Mapping(target = "id", ignore = true)
    TagResponse toResponse(TagEntity tagEntity);

    TagEntity toEntity(TagRequest tagRequest);
}
