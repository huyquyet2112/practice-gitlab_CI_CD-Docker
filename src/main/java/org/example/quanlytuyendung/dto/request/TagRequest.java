package org.example.quanlytuyendung.dto.request;

import lombok.Data;

@Data
public class TagRequest {
    private Integer id;
    private String name;
    private Boolean isActive;
}
