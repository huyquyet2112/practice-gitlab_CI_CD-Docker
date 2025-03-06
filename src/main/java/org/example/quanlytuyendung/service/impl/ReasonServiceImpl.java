package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.ReasonRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.ReasonResponse;
import org.example.quanlytuyendung.dto.response.TagResponse;
import org.example.quanlytuyendung.entity.GroupReasonEntity;
import org.example.quanlytuyendung.entity.ReasonEntity;
import org.example.quanlytuyendung.mapper.ReasonMapper;
import org.example.quanlytuyendung.repository.GroupReasonRepository;
import org.example.quanlytuyendung.repository.ReasonRepository;
import org.example.quanlytuyendung.service.ReasonService;
import org.example.quanlytuyendung.specification.BaseSpecification;
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
public class ReasonServiceImpl implements ReasonService {
    private final ReasonRepository reasonRepository;
    private final GroupReasonRepository groupReasonRepository;
    private final ReasonMapper reasonMapper;

    @Override
    public ApiResponse<PageableResponse<ReasonResponse>> findAllReason(int page, int size, ReasonResponse reasonResponse) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Map<String,Object> filter = new HashMap<>();
        if (reasonResponse.getName() != null) {
            filter.put("name", reasonResponse.getName());
        }
        Specification<ReasonEntity> specification = new BaseSpecification<>(filter);
        var pageableResponse = reasonRepository.findAll(specification,pageable);
        PageableResponse<ReasonResponse> response = PageableResponse.<ReasonResponse>builder()
                .page(page)
                .size(size)
                .totalPages(pageableResponse.getTotalPages())
                .totalElements(pageableResponse.getTotalElements())
                .numberOfElements(pageableResponse.getNumberOfElements())
                .content(pageableResponse.getContent().stream().map(reasonMapper::toResponse).collect(Collectors.toList()))
                .build();
        return new ApiResponse<>(response);
    }

    @Override
    public ReasonResponse addReason(ReasonRequest reasonRequest) {
        if (reasonRequest.getGroupReason() == null || reasonRequest.getGroupReason().getId() == null) {
            throw new RuntimeException("GroupReason ID không được để trống");
        }
        GroupReasonEntity groupReasonEntity = groupReasonRepository.findById(reasonRequest.getGroupReason().getId())
                .orElseThrow(() -> new RuntimeException("GroupReason không tồn tại"));
        ReasonEntity reasonEntity = reasonMapper.toEntity(reasonRequest);
        reasonEntity.setGroupReason(groupReasonEntity);

        reasonEntity = reasonRepository.save(reasonEntity);
        return new ReasonResponse(reasonEntity.getId());
    }

    @Override
    public ReasonResponse updateReason(ReasonRequest reasonRequest) {
        ReasonEntity reasonEntity = reasonRepository.findById(reasonRequest.getId()).orElseThrow(() -> new RuntimeException("Job Position not found!"));
        reasonEntity.setName(reasonRequest.getName());
        reasonEntity.setIsActive(reasonRequest.getIsActive());
        GroupReasonEntity groupReasonEntity = groupReasonRepository.findById(reasonRequest.getGroupReason().getId())
                .orElseThrow(() -> new RuntimeException("GroupReason không tồn tại"));
        reasonEntity.setGroupReason(groupReasonEntity);
        reasonRepository.save(reasonEntity);
        return new ReasonResponse(reasonEntity.getId());
    }

    @Override
    public ReasonResponse findReason(Integer id) {
        ReasonEntity reasonEntity = reasonRepository.findById(id).orElseThrow(() -> new RuntimeException("Reason not found!"));
        return reasonMapper.toResponse(reasonEntity);
    }

    @Override
    public ReasonEntity deleteReason(Integer id) {
        ReasonEntity reasonEntity = reasonRepository.findById(id).orElseThrow(() -> new RuntimeException("Reason not found!"));
        reasonRepository.delete(reasonEntity);
        return null;
    }

}
