package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LevelResponse {
    private Integer id;
    private String name;
    private String code;
    private String description;
    private Boolean isActive;

    public LevelResponse(Integer id) {
   this.id = id;
    }
}
