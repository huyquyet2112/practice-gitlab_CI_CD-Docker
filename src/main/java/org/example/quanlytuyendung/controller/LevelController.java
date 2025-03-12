package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.LevelRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.LevelResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.LevelEntity;
import org.example.quanlytuyendung.service.LevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/level")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<LevelResponse>>> getLevels(
            @RequestParam(defaultValue = "0" , required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(required = false)String search,
            @RequestParam (defaultValue = "createdAt:DESC")String sort
    ){
        ApiResponse<PageableResponse<LevelResponse>> apiResponse = levelService.getLevels(page,size,search,sort);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse <LevelResponse>> addLevel(@RequestBody LevelRequest levelRequest) {
        ApiResponse <LevelResponse> levelResponse = levelService.addLevel(levelRequest);
        return new ResponseEntity<>(levelResponse, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ApiResponse <LevelResponse>> updateLevel(@RequestBody LevelRequest levelRequest) {
        ApiResponse <LevelResponse> levelResponse = levelService.updateLevel(levelRequest);
        return new ResponseEntity<>(levelResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<LevelResponse>> getLevel(@RequestParam int id) {
       ApiResponse<LevelResponse> levelResponse = levelService.getLevel(id);
        return new ResponseEntity<>(levelResponse, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<LevelEntity> deleteLevel(@RequestParam int id) {
        LevelEntity levelEntity = levelService.deleteLevel(id);
        return new ResponseEntity<>(levelEntity, HttpStatus.OK);
    }
}
