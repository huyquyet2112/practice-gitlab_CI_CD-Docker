package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.DocumentRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.DocumentResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.QuestionResponse;
import org.example.quanlytuyendung.entity.DocumentEntity;
import org.example.quanlytuyendung.mapper.DocumentMapper;
import org.example.quanlytuyendung.repository.DocumentRepository;
import org.example.quanlytuyendung.service.DocumentService;
import org.example.quanlytuyendung.specification.BaseSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Override
    public ApiResponse<PageableResponse<DocumentResponse>> getDocument(int page, int size, String search, String sort) {
        String [] sortParam = sort.split(":");
        String sortField = sortParam[0];
        Sort.Direction sortDirection = sortParam.length>1 && sortParam[1].equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort orders = Sort.by(sortDirection , sortField);
        Pageable pageable = PageRequest.of(page, size, orders);
        Map<String,Object> filter = new HashMap<>();
       if(search != null && !search.isEmpty()){
           filter.put("search", search);
           filter.put("code", search);
       }
        Specification<DocumentEntity> spec = new BaseSpecification<>(filter);
        var result = documentRepository.findAll(spec,pageable);
        PageableResponse<DocumentResponse> pageableResponse = PageableResponse.<DocumentResponse>builder()
                .page(page)
                .size(size)
                .sort(orders.toString())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .numberOfElements(result.getNumberOfElements())
                .content(result.getContent().stream().map(documentMapper::toResponse).toList())
                .build();

        return new ApiResponse<>(pageableResponse);
    }

    @Override
    public ApiResponse<DocumentResponse> createDocument(DocumentRequest documentRequest) {
       DocumentEntity documentEntity = documentMapper.toEntity(documentRequest);
       documentRepository.save(documentEntity);
        return new ApiResponse<>(new DocumentResponse(documentEntity.getId()));
    }

    @Override
    public ApiResponse<DocumentResponse> updateDocument(DocumentRequest documentRequest) {
        DocumentEntity documentEntity = documentRepository.findById(documentRequest.getId()).orElse(null);
        documentEntity.setName(documentRequest.getName());
        documentEntity.setCode(documentRequest.getCode());
        documentEntity.setDescription(documentRequest.getDescription());
        documentEntity.setIsActive(documentRequest.getIsActive());
        documentRepository.save(documentEntity);
        return new ApiResponse<>(new DocumentResponse(documentEntity.getId()));
    }

    @Override
    public ApiResponse<DocumentResponse> getDocumentById(int id) {
        DocumentEntity documentEntity = documentRepository.findById(id).orElse(null);
    return new ApiResponse<>(documentMapper.toResponse(documentEntity));
    }

    @Override
    public DocumentEntity deleteDocumentById(int id) {
        DocumentEntity documentEntity = documentRepository.findById(id).orElse(null);
        documentRepository.delete(documentEntity);
        return null;
    }
}
