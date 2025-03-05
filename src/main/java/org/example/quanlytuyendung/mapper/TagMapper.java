package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.TagRequest;
import org.example.quanlytuyendung.dto.response.TagResponse;
import org.example.quanlytuyendung.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    // Chuyển từ TagEntity sang TagResponse
    TagResponse toResponse(TagEntity tagEntity);

    // Chuyển từ TagRequest sang TagEntity
    TagEntity toEntity(TagRequest tagRequest);
}
