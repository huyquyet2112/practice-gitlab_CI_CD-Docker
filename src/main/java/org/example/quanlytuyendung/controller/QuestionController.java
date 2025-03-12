package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.QuestionRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.QuestionResponse;
import org.example.quanlytuyendung.entity.QuestionEntity;
import org.example.quanlytuyendung.entity.ReasonEntity;
import org.example.quanlytuyendung.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/question")
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<QuestionResponse>>> getAllQuestion(
            @RequestParam(defaultValue = "0" , required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(required = false)String search,
            @RequestParam(defaultValue = "createdAt:DESC")String sort

    ){
        ApiResponse<PageableResponse<QuestionResponse>> apiResponse = questionService.findAll(page,size,search,sort);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<QuestionResponse>> addQuestion(@RequestBody QuestionRequest questionRequest) {
        ApiResponse<QuestionResponse> questionResponseApiResponse = questionService.addQuestion(questionRequest);
        return new ResponseEntity<>(questionResponseApiResponse, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<QuestionResponse>> updateQuestion(@RequestBody QuestionRequest questionRequest) {
        ApiResponse<QuestionResponse> questionResponseApiResponse = questionService.updateQuestion(questionRequest);
        return new ResponseEntity<>(questionResponseApiResponse, HttpStatus.OK);
    }@GetMapping
    public ResponseEntity<ApiResponse<QuestionResponse>> getQuestionById(@RequestParam int id) {
        ApiResponse<QuestionResponse> questionResponseApiResponse = questionService.getQuestionById(id);
        return new ResponseEntity<>(questionResponseApiResponse, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<QuestionEntity> deleteQuestionById(@RequestParam int id) {
        QuestionEntity questionEntity = questionService.deleteQuestionById(id);
        return new ResponseEntity<>(questionEntity, HttpStatus.OK);
    }
}
