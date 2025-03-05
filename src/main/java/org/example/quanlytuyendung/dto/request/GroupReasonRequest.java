package org.example.quanlytuyendung.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupReasonRequest {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private Boolean isActive;
}
