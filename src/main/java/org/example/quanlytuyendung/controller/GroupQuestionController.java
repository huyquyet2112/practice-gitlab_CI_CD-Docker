package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.GroupQuestionRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.GroupQuestionResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.GroupQuestionEntity;
import org.example.quanlytuyendung.service.GroupQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group-question")
public class GroupQuestionController {
    private final GroupQuestionService groupQuestionService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<GroupQuestionResponse>>> getGroupQuestions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "createdAt:DESC") String sort
    ){

        ApiResponse<PageableResponse<GroupQuestionResponse>> groupQuestionResponsePageableResponse = groupQuestionService.getGroupQuestions(page,size,search,sort);
        return new ResponseEntity<>(groupQuestionResponsePageableResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<GroupQuestionResponse>> addGroupQuestion(@RequestBody GroupQuestionRequest groupQuestionRequest){
        ApiResponse<GroupQuestionResponse> groupQuestionResponseApiResponse = groupQuestionService.addGroupQuestion(groupQuestionRequest);
        return new ResponseEntity<>(groupQuestionResponseApiResponse, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<GroupQuestionResponse>> updateGroupQuestion(@RequestBody GroupQuestionRequest groupQuestionRequest){
        ApiResponse<GroupQuestionResponse> groupQuestionResponseApiResponse = groupQuestionService.updateGroupQuestion(groupQuestionRequest);
        return new ResponseEntity<>(groupQuestionResponseApiResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<GroupQuestionResponse>> getGroupQuestionById(@RequestParam int id){
       ApiResponse< GroupQuestionResponse> groupQuestionResponse = groupQuestionService.getGroupQuestionById(id);
        return new ResponseEntity<>(groupQuestionResponse, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<GroupQuestionEntity> deleteGroupQuestionById(@RequestParam int id){
        GroupQuestionEntity groupQuestionEntity = groupQuestionService.deleteGroupQuestionById(id);
        return new ResponseEntity<>(groupQuestionEntity, HttpStatus.OK);
    }
}
