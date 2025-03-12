package org.example.quanlytuyendung.controller;

import lombok.RequiredArgsConstructor;
import org.example.quanlytuyendung.dto.request.TagRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.TagResponse;
import org.example.quanlytuyendung.entity.TagEntity;
import org.example.quanlytuyendung.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;


    @GetMapping("/list")
    public ResponseEntity <ApiResponse<PageableResponse<TagResponse>>> getAllTags(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "createdAt:DESC") String sort

    ) {

        ApiResponse<PageableResponse<TagResponse>> apiResponse = tagService.getAllTag(page,size,search,sort);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<TagResponse>> createTag(@RequestBody TagRequest tagRequest) {
        ApiResponse<TagResponse> tagResponse = tagService.addTagg(tagRequest);
        return ResponseEntity.ok(tagResponse);
    }
   @PutMapping
    public ResponseEntity<ApiResponse<TagResponse>> updateTag(@RequestBody TagRequest tagRequest) {
       ApiResponse<TagResponse> tagResponse = tagService.updateTag(tagRequest);
        return ResponseEntity.ok(tagResponse);
   }
   @GetMapping
    public ResponseEntity<ApiResponse<TagResponse>> getTag(@RequestParam int id) {
       ApiResponse<TagResponse> tagResponse = tagService.getTag(id);
        return ResponseEntity.ok(tagResponse);
   }
   @DeleteMapping
    public ResponseEntity<TagEntity> deleteTag(@RequestParam int id) {
        TagEntity tagEntity = tagService.deleteTag(id);
        return ResponseEntity.ok(tagEntity);
   }

}
