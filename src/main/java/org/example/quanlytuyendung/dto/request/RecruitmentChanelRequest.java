package org.example.quanlytuyendung.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentChanelRequest {
    private Integer id;
    private CandicateSourceRequest candicateSource;
    private String name;
    private Boolean isActive;
    private String description;
}
