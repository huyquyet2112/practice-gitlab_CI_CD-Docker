package org.example.quanlytuyendung.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentResponse {
    private Integer id;
    private String name;

    public DepartmentResponse(int departmentId) {
        this.id = departmentId;
    }

    public DepartmentResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
