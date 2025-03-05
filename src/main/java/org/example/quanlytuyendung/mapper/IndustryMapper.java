package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.IndustryRequest;
import org.example.quanlytuyendung.dto.response.IndustryResponse;
import org.example.quanlytuyendung.entity.IndustryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndustryMapper {



    IndustryEntity toModel(IndustryRequest request);


    IndustryResponse toResponse(IndustryEntity model);

    @Named("toResponseName")
    default IndustryResponse toResponseName(IndustryEntity industry) {
        if (industry == null) {
            return null;
        }
        IndustryResponse response = new IndustryResponse();
        response.setId(industry.getId());
        response.setName(industry.getName());
        return response;
    }


}
