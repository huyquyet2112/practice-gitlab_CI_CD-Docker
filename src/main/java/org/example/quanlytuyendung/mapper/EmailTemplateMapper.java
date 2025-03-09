package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.EmailTemplateRequest;
import org.example.quanlytuyendung.dto.response.EmailTemplateResponse;
import org.example.quanlytuyendung.entity.EmailTemplateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailTemplateMapper {
    @Mapping(target = "id",ignore = true)
    EmailTemplateResponse toResponse(EmailTemplateEntity emailTemplateEntity);

    @Mapping(target = "id",ignore = true)
    EmailTemplateEntity toEntity(EmailTemplateRequest emailTemplateRequest);
}
