package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.LevelRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.LevelResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.RequirementResponse;
import org.example.quanlytuyendung.entity.LevelEntity;
import org.example.quanlytuyendung.mapper.LevelMapper;
import org.example.quanlytuyendung.mapper.RequirementMapper;
import org.example.quanlytuyendung.repository.LevelRepository;
import org.example.quanlytuyendung.service.LevelService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;
    @Override
    public ApiResponse<PageableResponse<LevelResponse>> getLevels(int page, int size,String search,String sort) {
        String [] sortParam = sort.split(":");
        String sortField = sortParam[0];
        Sort.Direction sortDirection = sortParam.length > 1 && sortParam[1].equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort orders = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(page, size, orders);
        Map<String, Object> filters = new HashMap<>();
        if (search != null && !search.isEmpty()) {
            filters.put("name", search);
            filters.put("code", search);
        }
        Specification<LevelEntity> spec = new BaseSpecification<>(filters);
        var result = levelRepository.findAll(spec, pageable);

        PageableResponse<LevelResponse> pageableResponse = PageableResponse.<LevelResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .numberOfElements(result.getNumberOfElements())
                .content(result.getContent().stream().map(levelMapper::toLevelResponse).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public ApiResponse<LevelResponse> addLevel(LevelRequest levelRequest) {
       LevelEntity levelEntity = levelMapper.toLevelEntity(levelRequest);
       levelRepository.save(levelEntity);
        return new ApiResponse<>(new LevelResponse(levelEntity.getId())) ;
    }

    @Override
    public ApiResponse<LevelResponse> updateLevel(LevelRequest levelRequest) {
        LevelEntity levelEntity = levelRepository.findById(levelRequest.getId()).get();
        levelEntity.setName(levelRequest.getName());
        levelEntity.setCode(levelRequest.getCode());
        levelEntity.setDescription(levelRequest.getDescription());
        levelEntity.setIsActive(levelRequest.getIsActive());
        levelRepository.save(levelEntity);
        return new ApiResponse<>(new LevelResponse(levelEntity.getId())) ;
    }

    @Override
    public ApiResponse<LevelResponse> getLevel(int id) {
        LevelEntity levelEntity = levelRepository.findById(id).get();
        return new ApiResponse<>(levelMapper.toLevelResponse(levelEntity)) ;
    }

    @Override
    public LevelEntity deleteLevel(int id) {
        LevelEntity levelEntity = levelRepository.findById(id).get();
        levelRepository.delete(levelEntity);
        return null;
    }
}
