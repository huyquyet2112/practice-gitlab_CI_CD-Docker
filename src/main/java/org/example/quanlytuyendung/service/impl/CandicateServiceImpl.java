package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.CandicateSourceRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.CandicateSourceResponse;
import org.example.quanlytuyendung.dto.response.GroupReasonResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.CandicateSourceEntity;
import org.example.quanlytuyendung.mapper.CandicateSourceMapper;
import org.example.quanlytuyendung.repository.CandicateSourceRepository;
import org.example.quanlytuyendung.service.CandicateSourceService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Service
public class CandicateServiceImpl implements CandicateSourceService {
    private final CandicateSourceRepository candicateSourceRepository;
    private final CandicateSourceMapper candicateSourceMapper;
    @Override
    public ApiResponse<PageableResponse<CandicateSourceResponse>> findAll(int page, int size,String search,String sort ) {
       String [] sortParam = sort.split(":");
       String  sortField = sortParam[0];
       Sort.Direction sortDirection = sortParam.length>1 && sortParam[1].equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort orders = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(page, size, orders);


        Map<String, Object> filter = new HashMap<>();

        if (search != null && !search.trim().isEmpty()){
            filter.put("code", search);
            filter.put("name", search);
        }

        Specification<CandicateSourceEntity> spec = new BaseSpecification<>(filter);
        var pageData = candicateSourceRepository.findAll(spec, pageable);


        PageableResponse<CandicateSourceResponse> pageableResponse = PageableResponse.<CandicateSourceResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(candicateSourceMapper::toResponse).collect(Collectors.toList()))
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public ApiResponse <CandicateSourceResponse> addCandicateSource(CandicateSourceRequest candicateSourceRequest) {
        CandicateSourceEntity candicateSourceEntity = candicateSourceMapper.toEntity(candicateSourceRequest);
        candicateSourceRepository.save(candicateSourceEntity);
        return new ApiResponse<>(new CandicateSourceResponse(candicateSourceEntity.getId()));
    }

    @Override
    public ApiResponse <CandicateSourceResponse> updateCandicateSource(CandicateSourceRequest candicateSourceRequest) {
        CandicateSourceEntity candicateSourceEntity = candicateSourceRepository.findById(candicateSourceRequest.getId()).orElseThrow(() -> new RuntimeException("Group Reason not found!"));
        candicateSourceEntity.setName(candicateSourceRequest.getName());
        candicateSourceEntity.setCode(candicateSourceRequest.getCode());
        candicateSourceEntity.setIsActive(candicateSourceRequest.getIsActive());
        candicateSourceEntity.setDescription(candicateSourceRequest.getDescription());
        candicateSourceRepository.save(candicateSourceEntity);
        return new ApiResponse<>(new CandicateSourceResponse(candicateSourceEntity.getId()));
    }

    @Override
    public CandicateSourceResponse getCandicateSource(int id) {
        CandicateSourceEntity candicateSourceEntity = candicateSourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Group Reason not found!"));
        return candicateSourceMapper.toResponse(candicateSourceEntity);
    }

    @Override
    public CandicateSourceEntity deleteCandicateSource(int id) {
        CandicateSourceEntity candicateSourceEntity = candicateSourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Group Reason not found!"));
        candicateSourceRepository.delete(candicateSourceEntity);
        return null;
    }

}
