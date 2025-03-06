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
            @RequestParam(required = false) String name

    ) {
        TagResponse tagResponse = new TagResponse();
        tagResponse.setName(name);
        ApiResponse<PageableResponse<TagResponse>> apiResponse = tagService.getAllTag(page,size,tagResponse);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<TagResponse> createTag(@RequestBody TagRequest tagRequest) {
        TagResponse tagResponse = tagService.addTagg(tagRequest);
        return ResponseEntity.ok(tagResponse);
    }
   @PutMapping
    public ResponseEntity<TagResponse> updateTag(@RequestBody TagRequest tagRequest) {
        TagResponse tagResponse = tagService.updateTag(tagRequest);
        return ResponseEntity.ok(tagResponse);
   }
   @GetMapping
    public ResponseEntity<TagResponse> getTag(@RequestParam int id) {
        TagResponse tagResponse = tagService.getTag(id);
        return ResponseEntity.ok(tagResponse);
   }
   @DeleteMapping
    public ResponseEntity<TagEntity> deleteTag(@RequestParam int id) {
        TagEntity tagEntity = tagService.deleteTag(id);
        return ResponseEntity.ok(tagEntity);
   }

}
