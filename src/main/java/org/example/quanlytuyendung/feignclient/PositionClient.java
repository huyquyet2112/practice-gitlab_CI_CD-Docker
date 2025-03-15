package org.example.quanlytuyendung.feignclient;

import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.DepartmentResponse;

import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.PositionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "positionClient", url = "https://resources-service.dev.apusplatform.com/api/v1/position")
public interface PositionClient {
    @GetMapping("/list")
    ApiResponse<PageableResponse<PositionResponse>> getPositionsByIds(@RequestParam("ids") List<Integer> ids);
}


