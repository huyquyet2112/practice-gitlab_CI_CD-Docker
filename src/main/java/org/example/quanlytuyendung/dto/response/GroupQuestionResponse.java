package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupQuestionResponse {
    private Integer id;
    private String name;
    private String code;
    private String description;

    private Boolean isActive;


    public GroupQuestionResponse(Integer id) {
        this.id = id;
    }
}
