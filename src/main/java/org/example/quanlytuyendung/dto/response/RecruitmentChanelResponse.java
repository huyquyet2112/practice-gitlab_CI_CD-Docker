package org.example.quanlytuyendung.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentChanelResponse {
    private Integer id;
    private CandicateSourceResponse candicateSource;
    private String name;
    private Boolean isActive;
    private String description;

    public RecruitmentChanelResponse(Integer id) {
        this.id = id;
    }
}
