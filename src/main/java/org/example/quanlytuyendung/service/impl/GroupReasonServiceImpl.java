package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.GroupReasonRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.GroupReasonResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.GroupReasonEntity;
import org.example.quanlytuyendung.mapper.GroupReasonMapper;
import org.example.quanlytuyendung.repository.GroupReasonRepository;
import org.example.quanlytuyendung.service.GroupReasonService;
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

@Service
@RequiredArgsConstructor
public class GroupReasonServiceImpl implements GroupReasonService {

    private final GroupReasonRepository groupReasonRepository;
    private final GroupReasonMapper groupReasonMapper;



    @Override
    public ApiResponse<PageableResponse<GroupReasonResponse>> findAll(int page, int size, GroupReasonResponse groupReasonResponse) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Map<String,Object> filters = new HashMap<>();
        if(groupReasonResponse.getCode() != null) filters.put("code", groupReasonResponse.getCode());
        if(groupReasonResponse.getName() != null) filters.put("name", groupReasonResponse.getName());
        Specification<GroupReasonEntity> spec = new BaseSpecification<>(filters) ;
        var pageData = groupReasonRepository.findAll(spec,pageable);
        PageableResponse<GroupReasonResponse> pageableResponse = PageableResponse.<GroupReasonResponse>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(groupReasonMapper::toResponse).collect(Collectors.toList()))
                .build();
        return new ApiResponse<>(pageableResponse) ;
    }

    @Override
    public GroupReasonResponse addGroupReason(GroupReasonRequest groupReasonRequest) {
        if (groupReasonRepository.existsByName(groupReasonRequest.getName())) {
            throw new IllegalArgumentException("Try again! Name already exists.");
        }
        GroupReasonEntity groupReasonEntity = groupReasonMapper.toEntity(groupReasonRequest);
        groupReasonRepository.save(groupReasonEntity);
        return new GroupReasonResponse(groupReasonEntity.getId());
    }

    @Override
    public GroupReasonResponse updateGroupReason(GroupReasonRequest groupReasonRequest) {
        GroupReasonEntity grouperReasonEntity = groupReasonRepository.findById(groupReasonRequest.getId()).orElseThrow(() -> new RuntimeException("Group Reason not found!")) ;
        grouperReasonEntity.setName(groupReasonRequest.getName());
        grouperReasonEntity.setCode(groupReasonRequest.getCode());
        grouperReasonEntity.setDescription(groupReasonRequest.getDescription());
        grouperReasonEntity.setIsActive(groupReasonRequest.getIsActive());
        groupReasonRepository.save(grouperReasonEntity);
        return new GroupReasonResponse(grouperReasonEntity.getId());
    }

    @Override
    public ApiResponse<GroupReasonResponse> detailsGroupReason(int id) {
        GroupReasonEntity groupReasonEntity = groupReasonRepository.findById(id).orElseThrow(() -> new RuntimeException("Group Reason not found!"));
        GroupReasonResponse groupReasonResponse = groupReasonMapper.toResponse(groupReasonEntity);
        return new ApiResponse<>(groupReasonResponse);
    }

    @Override
    public GroupReasonEntity deleteGroupReason(int id) {
       GroupReasonEntity groupReasonEntity = groupReasonRepository.findById(id).orElseThrow(() -> new RuntimeException("Group Reason not found!"));
       groupReasonRepository.delete(groupReasonEntity);
        return null;
    }
}
