package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequirementResponse {
    private Integer id;
    private String name;
    private String description;
    private Boolean isActive;
    private DepartmentResponse department;


    public RequirementResponse(Integer id) {
        this.id = id;
    }
}
