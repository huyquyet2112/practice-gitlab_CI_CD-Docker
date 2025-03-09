package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.response.BenifitResponse;
import org.example.quanlytuyendung.dto.response.DepartmentResponse;
import org.example.quanlytuyendung.entity.BenifitEntity;
import org.example.quanlytuyendung.entity.BenifitMapEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BenifitMapper {

    BenifitMapper INSTANCE = Mappers.getMapper(BenifitMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "benifitMap", target = "department", qualifiedByName = "mapDepartment1")
    BenifitResponse mapBenifit(BenifitEntity benifit, BenifitMapEntity benifitMap);

    @Named("mapDepartment1")
    static DepartmentResponse mapDepartment(BenifitMapEntity benifitMap) {
        if (benifitMap == null) {
            return null;
        }
        return new DepartmentResponse(benifitMap.getDepartment());
    }
}