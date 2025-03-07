package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.LevelRequest;
import org.example.quanlytuyendung.dto.response.LevelResponse;
import org.example.quanlytuyendung.entity.LevelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LevelMapper {
    @Mapping(target = "id", ignore = true)
    LevelResponse toLevelResponse(LevelEntity levelEntity);

    @Mapping(target = "id", ignore = true)
    LevelEntity toLevelEntity(LevelRequest levelRequest);
}
