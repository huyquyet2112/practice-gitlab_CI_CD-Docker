package org.example.quanlytuyendung.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentRoundRequest {
    private Integer id;
    private RecruitmentRoundRequest recruitmentRoundType;
    private String name;
    private String code;
    private String description;
    private Boolean isActive;

}
