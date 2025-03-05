package org.example.quanlytuyendung.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagRequest {
    private Integer id;
    private String name;
    private Boolean isActive;
}
