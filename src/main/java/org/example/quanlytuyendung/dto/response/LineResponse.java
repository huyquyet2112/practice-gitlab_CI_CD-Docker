package org.example.quanlytuyendung.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LineResponse {
    private DepartmentResponse department;
    private List<PositionResponse> positions;

}