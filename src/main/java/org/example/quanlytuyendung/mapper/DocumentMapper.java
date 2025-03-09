package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.DocumentRequest;
import org.example.quanlytuyendung.dto.response.DocumentResponse;
import org.example.quanlytuyendung.entity.DocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    @Mapping(target = "id",ignore = true)
    DocumentResponse toResponse(DocumentEntity documentEntity);
    @Mapping(target = "id",ignore = true)
    DocumentEntity toEntity(DocumentRequest documentRequest);
}
