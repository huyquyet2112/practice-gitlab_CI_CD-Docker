package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.GroupQuestionRequest;
import org.example.quanlytuyendung.dto.response.GroupQuestionResponse;
import org.example.quanlytuyendung.entity.GroupQuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupQuestionMapper {
    @Mapping(target = "id",ignore = true)
    GroupQuestionResponse toResponse(GroupQuestionEntity groupQuestionEntity);
    @Mapping(target = "id",ignore = true)
    GroupQuestionEntity toEntity(GroupQuestionRequest groupReasonRequest);

}
