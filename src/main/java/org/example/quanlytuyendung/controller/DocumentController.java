package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.DocumentRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.BenifitResponse;
import org.example.quanlytuyendung.dto.response.DocumentResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.entity.DocumentEntity;
import org.example.quanlytuyendung.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/document")
public class DocumentController {
    private final DocumentService documentService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageableResponse<DocumentResponse>>>  getDocument(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "createdAt:DESC") String sort
    ){

        ApiResponse<PageableResponse<DocumentResponse>> documentResponseApiResponse = documentService.getDocument(page,size,search,sort);
        return  ResponseEntity.ok(documentResponseApiResponse);

    }
    @PostMapping
    public ResponseEntity<ApiResponse<DocumentResponse>> createDocument(@RequestBody DocumentRequest documentRequest){
        ApiResponse<DocumentResponse> documentResponseApiResponse = documentService.createDocument(documentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(documentResponseApiResponse);
    }
    @PutMapping ResponseEntity<ApiResponse<DocumentResponse>> updateDocument(@RequestBody DocumentRequest documentRequest){
        ApiResponse<DocumentResponse> documentResponseApiResponse = documentService.updateDocument(documentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(documentResponseApiResponse);
    }
    @GetMapping ResponseEntity<ApiResponse<DocumentResponse>> getDocumentById(@PathVariable int id){
        ApiResponse<DocumentResponse> documentResponseApiResponse = documentService.getDocumentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(documentResponseApiResponse);
    }
    @DeleteMapping ResponseEntity<DocumentEntity> deleteDocumentById(@PathVariable int id){
        DocumentEntity documentEntity = documentService.deleteDocumentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(documentEntity);
    }
}
