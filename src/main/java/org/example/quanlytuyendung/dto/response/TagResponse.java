package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TagResponse {
    private Integer id;
    private String name;
    private Boolean isActive;

    public TagResponse(Integer id) {
        this.id = id;
    }
}
