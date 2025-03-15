package org.example.quanlytuyendung.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.quanlytuyendung.dto.request.BenifitRequest;
import org.example.quanlytuyendung.dto.request.DepartmentRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.BenifitResponse;
import org.example.quanlytuyendung.dto.response.DepartmentResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.BenifitEntity;

import org.example.quanlytuyendung.entity.BenifitMapEntity;
import org.example.quanlytuyendung.feignclient.DepartmentClient;
import org.example.quanlytuyendung.mapper.BenifitMapper;
import org.example.quanlytuyendung.repository.BenifitMapRepsitory;
import org.example.quanlytuyendung.repository.BenifitRepository;
import org.example.quanlytuyendung.service.BenifitService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BenifitServiceImpl implements BenifitService {
    private final BenifitRepository benifitRepository;
    private final BenifitMapRepsitory benifitMapRepsitory;
    private final BenifitMapper benifitMapper;

    private final DepartmentClient departmentClient;


    @Override
    public ApiResponse<PageableResponse<BenifitResponse>> getBenifit(int page, int size, String search, String sort) {
        String[] sortParams = sort.split(":");
        String sortField = sortParams[0];
        Sort.Direction sortDirection = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("ASC")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Sort orders = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(page, size, orders);
        Map<String, Object> filter = new HashMap<>();
        if (search != null && !search.trim().isEmpty()) {
            filter.put("name", search);
            filter.put("code", search);
        }

        Specification<BenifitEntity> specification = new BaseSpecification<>(filter);
        Page<BenifitEntity> result = benifitRepository.findAll(specification, pageable);

        PageableResponse<BenifitResponse> pageableResponse = PageableResponse.<BenifitResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .numberOfElements(result.getNumberOfElements())
                .content(result.getContent().stream().map(benifit -> {

                    List<BenifitMapEntity> benifitMaps = benifitMapRepsitory.findAllByBenifit(benifit);
                    List<Integer> departmentId = benifitMaps.stream().map(BenifitMapEntity::getDepartment).toList();
                    List<DepartmentResponse> departmentResponses = new ArrayList<>();
                    if(!departmentId.isEmpty()) {
                        ApiResponse<PageableResponse<DepartmentResponse>> departmentResponseApiResponse = departmentClient.getDepartmentsByIds(departmentId);
                        departmentResponses = departmentResponseApiResponse.getData().getContent();
                    }

                    return benifitMapper.mapBenifit1(benifit, benifitMaps,departmentResponses);
                }).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }



    @Override
    public ApiResponse<BenifitResponse> postBenifit(BenifitRequest benifitRequest) {
        BenifitEntity benifit = new BenifitEntity();
        benifit.setCode(benifitRequest.getCode());
        benifit.setName(benifitRequest.getName());
        benifit.setDescription(benifitRequest.getDescription());
        benifit.setIsActive(benifitRequest.getIsActive());
        benifitRepository.save(benifit);
        List<BenifitMapEntity> benifitMapEntityList = new ArrayList<>();
        for(DepartmentRequest departmentRequest : benifitRequest.getDepartment()){
            BenifitMapEntity benifitMapEntity = new BenifitMapEntity();
            benifitMapEntity.setBenifit(benifit);
            benifitMapEntity.setDepartment(departmentRequest.getId());
            benifitMapEntityList.add(benifitMapEntity);
        }
        benifitMapRepsitory.saveAll(benifitMapEntityList);
        return new ApiResponse<>(new BenifitResponse(benifit.getId()));



    }

    @Override
    @Transactional
    public ApiResponse<BenifitResponse> updateBenifit(BenifitRequest benifitRequest) {
        BenifitEntity benifit = benifitRepository.findById(benifitRequest.getId())
                .orElseThrow(() -> new RuntimeException("Benefit not found for ID: " + benifitRequest.getId()));

        benifit.setDescription(benifitRequest.getDescription());
        benifit.setIsActive(benifitRequest.getIsActive());
        benifit.setCode(benifitRequest.getCode());
        benifit.setName(benifitRequest.getName());
        benifitRepository.save(benifit);

        List<BenifitMapEntity> benifitMapEntityList = benifitMapRepsitory.findByBenifit(benifit);
        Set<Integer> departmentId = benifitRequest.getDepartment().stream().map(DepartmentRequest::getId).collect(Collectors.toSet());
        List<BenifitMapEntity> benifitMapEntities = benifitMapEntityList.stream().filter(item -> !departmentId.contains(item.getId())).collect(Collectors.toList());
        benifitMapRepsitory.deleteAll(benifitMapEntities);
        List<BenifitMapEntity> toAdd = new ArrayList<>();
        for(Integer newdepartmentId : departmentId){
            boolean exists = benifitMapRepsitory.existsById(newdepartmentId);
            if(exists){
                BenifitMapEntity benifitMapEntity = new BenifitMapEntity();
                benifitMapEntity.setDepartment(newdepartmentId);
                benifitMapEntity.setBenifit(benifit);
                toAdd.add(benifitMapEntity);
            }
        }
        benifitMapRepsitory.saveAll(toAdd);
        return new ApiResponse<>(new BenifitResponse(benifit.getId()));
    }



    @Override
    public ApiResponse<BenifitResponse> getBenifitId(int id) {
        BenifitEntity benifit = benifitRepository.findById(id).orElse(null);

        List<BenifitMapEntity> benifitMaps = benifitMapRepsitory.findAllByBenifit(benifit);
        List<Integer> departmentIds = benifitMaps.stream()
                .map(BenifitMapEntity::getDepartment)
                .toList();


        List<DepartmentResponse> departmentList = new ArrayList<>();
        if (!departmentIds.isEmpty()) {
            ApiResponse<PageableResponse<DepartmentResponse>> response =
                    departmentClient.getDepartmentsByIds(departmentIds);
            departmentList = response.getData().getContent();
        }
        BenifitResponse benifitResponse = benifitMapper.mapBenifit1(benifit, benifitMaps, departmentList);

        return new ApiResponse<>(benifitResponse);
    }



    @Override
    public BenifitEntity deleteBenifit(int id) {
        BenifitEntity benifit = benifitRepository.findById(id).orElse(null);
        List<BenifitMapEntity> benifitMapEntity = benifitMapRepsitory.findByBenifit(benifit);
        benifitMapRepsitory.deleteAll(benifitMapEntity);
        benifitRepository.delete(benifit);
        return null;
    }
}
