package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.BenifitRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.BenifitResponse;
import org.example.quanlytuyendung.dto.response.DepartmentResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.BenifitEntity;

import org.example.quanlytuyendung.entity.BenifitMapEntity;
import org.example.quanlytuyendung.feignclient.DepartmentClient;
import org.example.quanlytuyendung.mapper.BenifitMapper;
import org.example.quanlytuyendung.repository.BenifitMapRepsitory;
import org.example.quanlytuyendung.repository.BenifitRepository;
import org.example.quanlytuyendung.service.BenifitService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class BenifitServiceImpl implements BenifitService {
    private final BenifitRepository benifitRepository;
    private final BenifitMapRepsitory benifitMapRepsitory;
    private final BenifitMapper benifitMapper;

    private final DepartmentClient departmentClient;


    @Override
    public ApiResponse<PageableResponse<BenifitResponse>> getBenifit(int page, int size, String search,String sort) {
        String[] sortParams = sort.split(":");
        String sortField = sortParams[0];
        Sort.Direction sortDirection = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("ASC")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Sort orders = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(page, size, orders);
        Map<String, Object> filter = new HashMap<>();
        if (search != null && !search.trim().isEmpty()) {
            filter.put("name", search);
            filter.put("code", search);
        }



        Specification<BenifitEntity> specification = new BaseSpecification<>(filter);
        Page<BenifitEntity> result = benifitRepository.findAll(specification, pageable);

        PageableResponse<BenifitResponse> pageableResponse = PageableResponse.<BenifitResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .numberOfElements(result.getNumberOfElements())
                .content(result.getContent().stream().map(benifit -> {
                    BenifitMapEntity benifitMap = benifitMapRepsitory.findByBenifit(benifit);
                    return benifitMapper.mapBenifit(benifit, benifitMap);
                }).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }


    @Override
    public ApiResponse<BenifitResponse> postBenifit(BenifitRequest benifitRequest) {
        BenifitEntity benifit = new BenifitEntity();
        benifit.setCode(benifitRequest.getCode());
        benifit.setName(benifitRequest.getName());
        benifit.setDescription(benifitRequest.getDescription());
        benifit.setIsActive(benifitRequest.getIsActive());
        BenifitMapEntity benifitMapEntity = new BenifitMapEntity();
        benifitMapEntity.setBenifit(benifit);
        benifitMapEntity.setDepartment(benifitRequest.getDepartment().getId());
        benifitRepository.save(benifit);
        benifitMapRepsitory.save(benifitMapEntity);
        return new ApiResponse<>(new BenifitResponse(benifit.getId()));



    }

    @Override
    public ApiResponse<BenifitResponse> updateBenifit(BenifitRequest benifitRequest) {
        BenifitEntity benifit = benifitRepository.findById(benifitRequest.getId()).orElse(null);
        benifit.setDescription(benifitRequest.getDescription());
        benifit.setIsActive(benifitRequest.getIsActive());
        benifit.setCode(benifitRequest.getCode());
        benifit.setName(benifitRequest.getName());
        BenifitMapEntity benifitMapEntity = benifitMapRepsitory.findById(benifit.getId()).orElse(null);

        benifitMapEntity.setDepartment(benifitRequest.getDepartment().getId());
        benifitRepository.save(benifit);
        benifitMapRepsitory.save(benifitMapEntity);
        return new ApiResponse<>(new BenifitResponse(benifit.getId()));
    }

    @Override
    public ApiResponse<BenifitResponse> getBenifitId(int id) {
        BenifitEntity benifit = benifitRepository.findById(id).orElse(null);
        if (benifit == null) {
            return new ApiResponse<>(null);
        }

        BenifitMapEntity benifitMapEntity = benifitMapRepsitory.findByBenifit(benifit);
        if (benifitMapEntity == null) {
            return new ApiResponse<>(null);
        }

        // Gọi Feign Client để lấy thông tin phòng ban
        ApiResponse<DepartmentResponse> departmentApiResponse = departmentClient.getDepartmentById(benifitMapEntity.getDepartment());
        DepartmentResponse department = departmentApiResponse.getData();

        BenifitResponse benifitResponse = new BenifitResponse();
        benifitResponse.setId(benifit.getId());
        benifitResponse.setName(benifit.getName());
        benifitResponse.setDescription(benifit.getDescription());
        benifitResponse.setCode(benifit.getCode());
        benifitResponse.setIsActive(benifit.getIsActive());
        benifitResponse.setDepartment(department); // Gán department đúng kiểu dữ liệu

        return new ApiResponse<>(benifitResponse);
    }


    @Override
    public BenifitEntity deleteBenifit(int id) {
        BenifitEntity benifit = benifitRepository.findById(id).orElse(null);
        BenifitMapEntity benifitMapEntity = benifitMapRepsitory.findByBenifit(benifit);
        benifitMapRepsitory.delete(benifitMapEntity);
        benifitRepository.delete(benifit);
        return null;
    }
}
