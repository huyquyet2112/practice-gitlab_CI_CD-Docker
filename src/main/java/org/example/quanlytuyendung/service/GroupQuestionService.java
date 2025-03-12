package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.GroupQuestionRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.GroupQuestionResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.GroupQuestionEntity;

public interface GroupQuestionService {
    ApiResponse<PageableResponse<GroupQuestionResponse>> getGroupQuestions(int page, int size,String search,String sort);

    ApiResponse<GroupQuestionResponse> addGroupQuestion(GroupQuestionRequest groupQuestionRequest);

    ApiResponse<GroupQuestionResponse> updateGroupQuestion(GroupQuestionRequest groupQuestionRequest);

    ApiResponse<GroupQuestionResponse> getGroupQuestionById(int id);

    GroupQuestionEntity deleteGroupQuestionById(int id);
}
