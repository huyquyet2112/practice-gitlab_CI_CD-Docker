package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.RecruitmentRoundTypeRequest;
import org.example.quanlytuyendung.dto.response.RecruitmentRoundTypeResponse;
import org.example.quanlytuyendung.entity.RecruitmentRoundTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import org.mapstruct.Named;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RecruitmentRoundTypeMapper {

    @Mapping(target = "id", ignore = true)
    RecruitmentRoundTypeResponse toResponse(RecruitmentRoundTypeEntity recruitmentRoundTypeEntity);

    @Mapping(target = "id", ignore = true)
    RecruitmentRoundTypeEntity toEntity(RecruitmentRoundTypeRequest request);




}



