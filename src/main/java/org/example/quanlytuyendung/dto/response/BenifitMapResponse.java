package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BenifitMapResponse {
    private Integer id;
 // Chứa toàn bộ thông tin từ BenifitResponse
    private DepartmentResponse department;

}
