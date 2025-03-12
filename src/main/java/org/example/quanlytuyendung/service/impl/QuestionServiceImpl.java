package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.QuestionRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.QuestionResponse;
import org.example.quanlytuyendung.entity.GroupQuestionEntity;
import org.example.quanlytuyendung.entity.GroupReasonEntity;
import org.example.quanlytuyendung.entity.QuestionEntity;
import org.example.quanlytuyendung.mapper.QuestionMapper;
import org.example.quanlytuyendung.repository.GroupQuestionRepository;
import org.example.quanlytuyendung.repository.GroupReasonRepository;
import org.example.quanlytuyendung.repository.QuestionRepository;
import org.example.quanlytuyendung.service.QuestionService;
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
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final GroupQuestionRepository groupQuestionRepository;

    @Override
    public ApiResponse<PageableResponse<QuestionResponse>> findAll(int page, int size,String search, String sort) {
        String [] sortParam = sort.split(":");
        String sortField = sortParam[0];
        Sort.Direction sortDirection = sortParam.length > 1 && sortParam[1].equalsIgnoreCase("ASC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort orders = Sort.by(sortDirection, sortField);
        Pageable questionPageable = PageRequest.of(page, size, orders);
        Map<String,Object> filter = new HashMap<>();
      if(search != null && !search.isEmpty()){
          filter.put("name",search);
      }
        Specification<QuestionEntity> specification = new BaseSpecification<>(filter);
        var questions = questionRepository.findAll(specification,questionPageable);
        PageableResponse<QuestionResponse> pageableResponse = PageableResponse.<QuestionResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .totalPages(questions.getTotalPages())
                .totalElements(questions.getTotalElements())
                .numberOfElements(questions.getNumberOfElements())
                .content(questions.getContent().stream().map(questionMapper::toResponse).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public ApiResponse<QuestionResponse> addQuestion(QuestionRequest questionRequest) {
        QuestionEntity questionEntity = questionMapper.toEntity(questionRequest);
        GroupQuestionEntity groupQuestionEntity = groupQuestionRepository.findById(questionRequest.getGroupQuestion().getId()).get();
        questionEntity.setGroupQuestion(groupQuestionEntity);
        questionRepository.save(questionEntity);

        return new ApiResponse<>(new QuestionResponse(questionEntity.getId()));
    }

    @Override
    public ApiResponse<QuestionResponse> updateQuestion(QuestionRequest questionRequest) {
        QuestionEntity questionEntity = questionRepository.findById(questionRequest.getId()).get();
        questionEntity.setName(questionRequest.getName());
        questionEntity.setDescription(questionRequest.getDescription());
        questionEntity.setGroupQuestion(questionEntity.getGroupQuestion());
        questionEntity.setIsActive(questionRequest.getIsActive());
        questionRepository.save(questionEntity);
        return new ApiResponse<>(new QuestionResponse(questionEntity.getId()));
    }

    @Override
    public ApiResponse<QuestionResponse> getQuestionById(int id) {
        QuestionEntity questionEntity = questionRepository.findById(id).get();
        return new ApiResponse<>(questionMapper.toResponse(questionEntity));
    }

    @Override
    public QuestionEntity deleteQuestionById(int id) {
        QuestionEntity  questionEntity = questionRepository.findById(id).get();
        questionRepository.delete(questionEntity);
        return null;
    }
}
