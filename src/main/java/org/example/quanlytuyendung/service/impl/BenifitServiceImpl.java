package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.BenifitRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.BenifitResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.BenifitEntity;

import org.example.quanlytuyendung.entity.BenifitMapEntity;
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
//    private final BenifitMapMapper benifitMapMapper;

    @Override
    public ApiResponse<PageableResponse<BenifitResponse>> getBenifit(int page, int size, BenifitResponse benifitResponse) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);

        Map<String, Object> filter = new HashMap<>();
        if (benifitResponse.getCode() != null) {
            filter.put("code", benifitResponse.getCode());
        }
        if (benifitResponse.getName() != null) {
            filter.put("name", benifitResponse.getName());
        }

        Specification<BenifitEntity> benifitEntitySpecification = new BaseSpecification<>(filter);
        Page<BenifitEntity> result = benifitRepository.findAll(benifitEntitySpecification, pageable);

        PageableResponse<BenifitResponse> pageableResponse = PageableResponse.<BenifitResponse>builder()
                .page(page)
                .size(size)
                .sort(sort.toString())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .numberOfElements(result.getNumberOfElements())
                .content(result.getContent().stream().map(benifit -> {

                    BenifitMapEntity benifitMap = benifitMapRepsitory.findByBenifit(benifit); // Giả sử bạn có phương thức này
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
        BenifitMapEntity benifitMapEntity = benifitMapRepsitory.findByBenifit(benifit);

        return new ApiResponse<>(benifitMapper.mapBenifit(benifit,benifitMapEntity));
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
