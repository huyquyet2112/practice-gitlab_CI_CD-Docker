package org.example.quanlytuyendung.service.impl;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;


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
               .content(tagEntities.getContent().stream().map(tagMapper::toResponse).collect(Collectors.toList()))
               .build();

        return new ApiResponse<>(pageableResponse) ;
    }

    @Override
    public TagResponse addTagg(TagRequest tagRequest) {
        if(tagRepository.existsByName(tagRequest.getName())) {
            throw new IllegalArgumentException("Try again! Name exists!");
        }
        TagEntity tagEntity = tagMapper.toEntity(tagRequest);
        tagRepository.save(tagEntity);
        return new TagResponse(tagEntity.getId());
    }

    @Override
    public TagResponse updateTag(TagRequest tagRequest) {
        TagEntity tagEntity = tagRepository.findById(tagRequest.getId()).orElseThrow(() -> new RuntimeException("Job Position not found!"));
        tagEntity.setName(tagRequest.getName());
        tagEntity.setIsActive(tagRequest.getIsActive());
        tagRepository.save(tagEntity);
        return new TagResponse(tagEntity.getId());
    }

    @Override
    public TagResponse getTag(int id) {
        TagEntity tagEntity = tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Job Position not found!"));
        return tagMapper.toResponse(tagEntity);
    }

    @Override
    public TagEntity deleteTag(int id) {
        TagEntity tagEntity = tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Job Position not found!"));
        tagRepository.delete(tagEntity);
        return null;
    }
}
