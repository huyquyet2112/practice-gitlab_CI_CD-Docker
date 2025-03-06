package org.example.quanlytuyendung.mapper;


import org.example.quanlytuyendung.dto.request.RecruitmentChanelRequest;
import org.example.quanlytuyendung.dto.response.CandicateSourceResponse;
import org.example.quanlytuyendung.dto.response.RecruitmentChanelResponse;
import org.example.quanlytuyendung.entity.CandicateSourceEntity;
import org.example.quanlytuyendung.entity.RecruitmentChanelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",uses = CandicateSourceMapper.class)
public interface RecruitmentChanelMapper {

    @Mapping(target = "id", ignore = true)

    RecruitmentChanelResponse toResponse(RecruitmentChanelEntity recruitmentChanelEntity);

    @Mapping(target = "id", ignore = true)
    RecruitmentChanelEntity toEntity(RecruitmentChanelRequest request);


}
