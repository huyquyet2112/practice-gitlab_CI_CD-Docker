package org.example.quanlytuyendung.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.quanlytuyendung.dto.response.BenifitResponse;
import org.example.quanlytuyendung.dto.response.DepartmentResponse;
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BenifitMapRequest {
    private Integer id;


    private DepartmentRequest department;
}
