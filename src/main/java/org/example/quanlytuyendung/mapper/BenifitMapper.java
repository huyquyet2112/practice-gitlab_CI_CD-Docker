package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.response.BenifitResponse;
import org.example.quanlytuyendung.dto.response.DepartmentResponse;
import org.example.quanlytuyendung.entity.BenifitEntity;
import org.example.quanlytuyendung.entity.BenifitMapEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

import java.util.Collections;
import java.util.List;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BenifitMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "benifitMap", target = "department", qualifiedByName = "mapDepartment1")
    BenifitResponse mapBenifit(BenifitEntity benifit, List<BenifitMapEntity> benifitMap);
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "departmentList", target = "department")
    BenifitResponse mapBenifit1(BenifitEntity benifit, List<BenifitMapEntity> benifitMaps, List<DepartmentResponse> departmentList);
    @Named("mapDepartment1")
    static List<DepartmentResponse> mapDepartment(List<BenifitMapEntity> benifitMaps) {
        if (benifitMaps == null || benifitMaps.isEmpty()) {
            return null;
        }
        return benifitMaps.stream()
                .map(map -> new DepartmentResponse(map.getDepartment()))
                .collect(Collectors.toList());
    }
}
