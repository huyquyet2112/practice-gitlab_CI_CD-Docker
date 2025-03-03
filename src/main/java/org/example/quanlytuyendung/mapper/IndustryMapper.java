package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.IndustryRequest;
import org.example.quanlytuyendung.dto.response.IndustryResponse;
import org.example.quanlytuyendung.entity.IndustryEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class IndustryMapper {
    public static IndustryEntity toModel(IndustryRequest request) {
        if (request == null) {
            return null;
        }
        return new IndustryEntity(
                0,
                request.getName(),
                request.getCode(),
                request.getDescription(),
                request.getIsActive(),
                null,null
        );
    }

    public static IndustryResponse toResponse(IndustryEntity model) {
        if (model == null) {
            return null;
        }
        return new IndustryResponse(
                null,
                model.getCode(),
                model.getName(),
                null,
                model.getIsActive()
        );
    }

    public static IndustryResponse toResponseDetails(IndustryEntity model) {
        if (model == null) {
            return null;
        }
        return new IndustryResponse(
                model.getId(),
                model.getCode(),
                model.getName(),
                model.getDescription(),
                model.getIsActive()
        );
    }
    public static IndustryResponse toResponseId(IndustryEntity model) {
        if (model == null) {
            return null;
        }
        return new IndustryResponse(
                model.getId(),
                null,
                null,
                null,
                null
        );
    }
    public static IndustryResponse toResponseName(IndustryEntity model) {
        if (model == null) {
            return null;
        }
        return new IndustryResponse(
                model.getId(),
                model.getName(),
                null,
                null,
                null
        );
    }


}
