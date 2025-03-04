package org.example.quanlytuyendung.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentResponse {
    private int id;
    private String name;

    public DepartmentResponse(int departmentId) {
    }
}
