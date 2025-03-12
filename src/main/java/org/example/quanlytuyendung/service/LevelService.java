package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.LevelRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.LevelResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.LevelEntity;

public interface LevelService {
    ApiResponse<PageableResponse<LevelResponse>> getLevels(int page, int size, String search, String sort);

    ApiResponse<LevelResponse> addLevel(LevelRequest levelRequest);

    ApiResponse<LevelResponse> updateLevel(LevelRequest levelRequest);

    ApiResponse<LevelResponse> getLevel(int id);

    LevelEntity deleteLevel(int id);
}
