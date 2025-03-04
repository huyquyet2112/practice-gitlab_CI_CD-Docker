package org.example.quanlytuyendung.service;

import org.example.quanlytuyendung.dto.request.TagRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.TagResponse;

public interface TagService {
    ApiResponse<PageableResponse<TagResponse>> getAllTag(int page, int size);

    TagResponse addTagg(TagRequest tagRequest);

    TagResponse updateTag(TagRequest tagRequest);
}
