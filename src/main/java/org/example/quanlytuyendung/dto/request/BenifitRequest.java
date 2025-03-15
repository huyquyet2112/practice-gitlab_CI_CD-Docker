package org.example.quanlytuyendung.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.quanlytuyendung.dto.response.BenifitMapResponse;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BenifitRequest {
    private Integer id;
    private String name;

    private String code;
    private List<DepartmentRequest> department;
    private Boolean isActive;
    private String description;

}