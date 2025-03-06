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
    public ApiResponse<PageableResponse<CandicateSourceResponse>> findAll(int page, int size, CandicateSourceResponse candicateSourceResponse) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);

        // Tạo filter từ dữ liệu đầu vào
        Map<String, Object> filter = new HashMap<>();
        if (candicateSourceResponse.getName() != null) {
            filter.put("name", candicateSourceResponse.getName());
        }
        if (candicateSourceResponse.getCode() != null) {
            filter.put("code", candicateSourceResponse.getCode());
        }

        Specification<CandicateSourceEntity> spec = new BaseSpecification<>(filter);
        var pageData = candicateSourceRepository.findAll(spec, pageable);


        PageableResponse<CandicateSourceResponse> pageableResponse = PageableResponse.<CandicateSourceResponse>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(candicateSourceMapper::toResponse).collect(Collectors.toList()))
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public CandicateSourceResponse addCandicateSource(CandicateSourceRequest candicateSourceRequest) {
        CandicateSourceEntity candicateSourceEntity = candicateSourceMapper.toEntity(candicateSourceRequest);
        candicateSourceRepository.save(candicateSourceEntity);
        return new CandicateSourceResponse(candicateSourceEntity.getId());
    }

    @Override
    public CandicateSourceResponse updateCandicateSource(CandicateSourceRequest candicateSourceRequest) {
        CandicateSourceEntity candicateSourceEntity = candicateSourceRepository.findById(candicateSourceRequest.getId()).orElseThrow(() -> new RuntimeException("Group Reason not found!"));
        candicateSourceEntity.setName(candicateSourceRequest.getName());
        candicateSourceEntity.setCode(candicateSourceRequest.getCode());
        candicateSourceEntity.setIsActive(candicateSourceRequest.getIsActive());
        candicateSourceEntity.setDescription(candicateSourceRequest.getDescription());
        candicateSourceRepository.save(candicateSourceEntity);
        return new CandicateSourceResponse(candicateSourceEntity.getId());
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
