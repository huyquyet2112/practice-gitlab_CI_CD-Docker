package org.example.quanlytuyendung.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.quanlytuyendung.dto.response.CandicateSourceResponse;
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentChanelRequest {
    private int id;
    private CandicateSourceRequest candicateSource;
    private String name;
    private Boolean isActive;
    private String description;
}
