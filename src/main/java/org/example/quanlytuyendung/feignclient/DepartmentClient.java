package org.example.quanlytuyendung.feignclient;

import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.DepartmentResponse;
import org.example.quanlytuyendung.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "resource-service", url = "https://resources-service.dev.apusplatform.com/api/v1/department",configuration = FeignClientConfiguration.class)
public interface DepartmentClient {
    @GetMapping(produces = "application/json")  // Đảm bảo trả về JSON
    ApiResponse<DepartmentResponse> getDepartmentById(@RequestParam("departmentId") Integer id);
}

