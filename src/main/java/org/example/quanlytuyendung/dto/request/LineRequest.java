package org.example.quanlytuyendung.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineRequest {
    @JsonProperty("department")
    private DepartmentRequest department;
    private List<PositionRequest> positions;
}
