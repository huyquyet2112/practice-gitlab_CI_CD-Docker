package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.GroupQuestionRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.GroupQuestionResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.WorkTypeResponse;
import org.example.quanlytuyendung.entity.GroupQuestionEntity;
import org.example.quanlytuyendung.mapper.GroupQuestionMapper;
import org.example.quanlytuyendung.repository.GroupQuestionRepository;
import org.example.quanlytuyendung.service.GroupQuestionService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GroupQuestionServiceImpl implements GroupQuestionService {
    private final GroupQuestionRepository groupQuestionRepository;
    private final GroupQuestionMapper groupQuestionMapper;

    @Override
    public ApiResponse<PageableResponse<GroupQuestionResponse>> getGroupQuestions(int page, int size, GroupQuestionResponse groupQuestionResponse) {
       Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Map<String,Object> filter = new HashMap<>();
        if(groupQuestionResponse.getId()!=null){
            filter.put("code",groupQuestionResponse.getCode());
        }
        if(groupQuestionResponse.getName()!=null){
            filter.put("name",groupQuestionResponse.getName());
        }
        Specification<GroupQuestionEntity> spec = new BaseSpecification<>(filter);
        var result = groupQuestionRepository.findAll(spec,pageable);

        PageableResponse<GroupQuestionResponse> pageableResponse = PageableResponse.<GroupQuestionResponse>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .numberOfElements(result.getNumberOfElements())
                .content(result.getContent().stream().map(groupQuestionMapper::toResponse).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public ApiResponse<GroupQuestionResponse> addGroupQuestion(GroupQuestionRequest groupQuestionRequest) {
        GroupQuestionEntity groupQuestionEntity = groupQuestionMapper.toEntity(groupQuestionRequest);
        groupQuestionRepository.save(groupQuestionEntity);
        return new ApiResponse<>(new GroupQuestionResponse(groupQuestionEntity.getId()));
    }

    @Override
    public ApiResponse<GroupQuestionResponse> updateGroupQuestion(GroupQuestionRequest groupQuestionRequest) {
        GroupQuestionEntity groupQuestionEntity = groupQuestionRepository.findById(groupQuestionRequest.getId()).orElse(null);
        groupQuestionEntity.setName(groupQuestionRequest.getName());
        groupQuestionEntity.setCode(groupQuestionRequest.getCode());
        groupQuestionEntity.setDescription(groupQuestionRequest.getDescription());
        groupQuestionEntity.setIsActive(groupQuestionRequest.getIsActive());
        groupQuestionRepository.save(groupQuestionEntity);
        return new ApiResponse<>(new GroupQuestionResponse(groupQuestionEntity.getId()));
    }

    @Override
    public ApiResponse<GroupQuestionResponse> getGroupQuestionById(int id) {
       GroupQuestionEntity groupQuestionEntity = groupQuestionRepository.findById(id).orElse(null);
        return new ApiResponse<>(groupQuestionMapper.toResponse(groupQuestionEntity));
    }

    @Override
    public GroupQuestionEntity deleteGroupQuestionById(int id) {
        GroupQuestionEntity groupQuestion = groupQuestionRepository.findById(id).orElse(null);
        groupQuestionRepository.delete(groupQuestion);
        return null;
    }
}
