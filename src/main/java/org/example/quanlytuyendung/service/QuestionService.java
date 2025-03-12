package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.QuestionRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.QuestionResponse;
import org.example.quanlytuyendung.entity.QuestionEntity;

public interface QuestionService {
    ApiResponse<PageableResponse<QuestionResponse>> findAll(int page, int size, String search, String sort);

    ApiResponse<QuestionResponse> addQuestion(QuestionRequest questionRequest);

    ApiResponse<QuestionResponse> updateQuestion(QuestionRequest questionRequest);

    ApiResponse<QuestionResponse> getQuestionById(int id);

    QuestionEntity deleteQuestionById(int id);
}
