package org.example.quanlytuyendung.controller;

import org.example.quanlytuyendung.dto.request.TagRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.TagResponse;
import org.example.quanlytuyendung.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }
    @GetMapping("/list")
    public ResponseEntity <ApiResponse<PageableResponse<TagResponse>>> getAllTags(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        ApiResponse<PageableResponse<TagResponse>> apiResponse = tagService.getAllTag(page,size);
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

}
