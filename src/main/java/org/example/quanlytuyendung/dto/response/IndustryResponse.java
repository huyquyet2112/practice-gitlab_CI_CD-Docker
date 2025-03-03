package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndustryResponse {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private Boolean isActive;



}
