package org.example.quanlytuyendung.service.impl;

import org.example.quanlytuyendung.dto.request.TagRequest;
import org.example.quanlytuyendung.dto.response.ApiResponse;
import org.example.quanlytuyendung.dto.response.PageableResponse;
import org.example.quanlytuyendung.dto.response.TagResponse;
import org.example.quanlytuyendung.entity.TagEntity;
import org.example.quanlytuyendung.mapper.TagMapper;
import org.example.quanlytuyendung.repository.TagRepository;
import org.example.quanlytuyendung.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public ApiResponse<PageableResponse<TagResponse>> getAllTag(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageData = PageRequest.of(page, size, sort);
        Page<TagEntity> tagEntities = tagRepository.findAll(pageData);
       PageableResponse<TagResponse> pageableResponse = PageableResponse.<TagResponse>builder()
               .page(page)
               .size(size)
               .sort(sort.toString())
               .totalPages(tagEntities.getTotalPages())
               .totalElements(tagEntities.getTotalElements())
               .totalElements(tagEntities.getTotalElements())
               .numberOfElements(tagEntities.getNumberOfElements())
               .content(tagEntities.getContent().stream().map(TagMapper::toResponse).collect(Collectors.toList()))
               .build();

        return new ApiResponse<>(pageableResponse) ;
    }

    @Override
    public TagResponse addTagg(TagRequest tagRequest) {
        if(tagRepository.existsByName(tagRequest.getName())) {
            throw new IllegalArgumentException("Try again! Name exists!");
        }
        TagEntity tagEntity = TagMapper.toTagEntity(tagRequest);
        tagRepository.save(tagEntity);
        return new TagResponse(tagEntity.getId());
    }

    @Override
    public TagResponse updateTag(TagRequest tagRequest) {
        TagEntity tagEntity = tagRepository.findById(tagRequest.getId()).orElseThrow(() -> new RuntimeException("Job Position not found!"));
        tagEntity.setName(tagRequest.getName());
        tagEntity.setActive(tagRequest.getIsActive());
        tagRepository.save(tagEntity);
        return new TagResponse(tagEntity.getId());
    }
}
