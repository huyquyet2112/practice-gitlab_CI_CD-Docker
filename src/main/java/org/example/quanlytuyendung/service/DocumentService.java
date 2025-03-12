package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.DocumentRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.DocumentResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.DocumentEntity;

public interface DocumentService {
    ApiResponse<PageableResponse<DocumentResponse>> getDocument(int page, int size,String search,String sort);

    ApiResponse<DocumentResponse> createDocument(DocumentRequest documentRequest);

    ApiResponse<DocumentResponse> updateDocument(DocumentRequest documentRequest);

    ApiResponse<DocumentResponse> getDocumentById(int id);

    DocumentEntity deleteDocumentById(int id);
}
