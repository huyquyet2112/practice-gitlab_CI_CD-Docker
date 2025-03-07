package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.RecruitmentRoundRequest;
import org.example.quanlytuyendung.dto.response.RecruitmentRoundResponse;
import org.example.quanlytuyendung.entity.RecruitmentRoundEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecruitmentRoundMapper {

    @Mapping(target = "id", ignore = true)
    RecruitmentRoundResponse toResponse(RecruitmentRoundEntity entity);

    RecruitmentRoundEntity toEntity(RecruitmentRoundRequest request);


}