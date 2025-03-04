package org.example.quanlytuyendung.mapper;

import org.example.quanlytuyendung.dto.request.TagRequest;
import org.example.quanlytuyendung.dto.response.TagResponse;
import org.example.quanlytuyendung.entity.TagEntity;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public static TagResponse toResponse(TagEntity tagEntity) {
        if (tagEntity == null) return null;
        return  new TagResponse(
                null,
                tagEntity.getName(),
                tagEntity.getActive()
        );

    }
    public static TagEntity toTagEntity(TagRequest tagRequest) {
        if (tagRequest == null) return null;
        return new TagEntity(
                null,
                tagRequest.getName(),
                tagRequest.getIsActive()
        );
    }

}
