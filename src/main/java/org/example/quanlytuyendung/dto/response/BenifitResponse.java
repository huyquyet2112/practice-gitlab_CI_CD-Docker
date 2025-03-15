package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BenifitResponse {
    private Integer id;
    private String name;
    private String code;

    private Boolean isActive;
    private String description;
    private List<DepartmentResponse> department;

    public BenifitResponse(Integer id) {
        this.id = id;
    }
}
