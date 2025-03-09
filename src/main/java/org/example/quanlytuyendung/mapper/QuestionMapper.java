package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.QuestionRequest;
import org.example.quanlytuyendung.dto.response.GroupQuestionResponse;
import org.example.quanlytuyendung.dto.response.QuestionResponse;
import org.example.quanlytuyendung.entity.GroupQuestionEntity;
import org.example.quanlytuyendung.entity.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "groupQuestion" , source = "groupQuestion" , qualifiedByName = "toGroupQuestion")
    QuestionResponse toResponse(QuestionEntity questionEntity);

    @Mapping(target = "id",ignore = true)
    QuestionEntity toEntity(QuestionRequest questionRequest);

    @Named("toGroupQuestion")
    default GroupQuestionResponse toGroupQuestion(GroupQuestionEntity groupQuestionEntity) {
        return new GroupQuestionResponse(groupQuestionEntity.getId(), groupQuestionEntity.getName());
    }
}
