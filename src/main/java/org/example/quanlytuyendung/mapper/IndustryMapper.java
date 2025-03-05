package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.IndustryRequest;
import org.example.quanlytuyendung.dto.response.IndustryResponse;
import org.example.quanlytuyendung.entity.IndustryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndustryMapper {


    IndustryEntity toModel(IndustryRequest request);

    IndustryResponse toResponse(IndustryEntity model);

    IndustryResponse toResponseDetails(IndustryEntity model);

    IndustryResponse toResponseId(IndustryEntity model);


    IndustryResponse toResponseName(IndustryEntity model);
}
