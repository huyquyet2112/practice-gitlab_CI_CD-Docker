package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.CandicateSourceRequest;
import org.example.quanlytuyendung.dto.request.GroupReasonRequest;
import org.example.quanlytuyendung.dto.response.CandicateSourceResponse;
import org.example.quanlytuyendung.dto.response.GroupReasonResponse;
import org.example.quanlytuyendung.entity.CandicateSourceEntity;
import org.example.quanlytuyendung.entity.GroupReasonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CandicateSourceMapper {
    CandicateSourceMapper INSTANCE = Mappers.getMapper(CandicateSourceMapper.class);
    @Mapping(target = "id",ignore = true)
    CandicateSourceResponse toResponse(CandicateSourceEntity entity);
    @Mapping(target = "id",ignore = true)
    CandicateSourceEntity toEntity(CandicateSourceRequest  candicateSourceRequest);

    @Mapping(target = "id",ignore = true)
    GroupReasonEntity toEntity(GroupReasonRequest request);
    @Named("toCandicateSourceResponse")
    static CandicateSourceResponse toCandicateSourceResponse(CandicateSourceEntity candicateSourceEntity) {
        if (candicateSourceEntity == null) {
            return null;
        }
        return new CandicateSourceResponse(candicateSourceEntity.getId(), candicateSourceEntity.getName());
    }
}
