package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.ReasonRequest;
import org.example.quanlytuyendung.dto.response.ReasonResponse;
import org.example.quanlytuyendung.dto.response.GroupReasonResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentRoundTypeResponse;
import org.example.quanlytuyendung.entity.ReasonEntity;
import org.example.quanlytuyendung.entity.GroupReasonEntity;
import org.example.quanlytuyendung.entity.RecruitmentRoundTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = GroupReasonMapper.class)
public interface ReasonMapper {
    ReasonMapper INSTANCE = Mappers.getMapper(ReasonMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groupReason", source = "groupReason", qualifiedByName = "toGroupReasonResponse")
    ReasonResponse toResponse(ReasonEntity reasonEntity);
    @Mapping(target = "id", ignore = true)
    ReasonEntity toEntity(ReasonRequest reasonRequest);


}

