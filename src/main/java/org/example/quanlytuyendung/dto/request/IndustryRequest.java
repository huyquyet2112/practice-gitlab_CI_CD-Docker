package org.example.quanlytuyendung.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IndustryRequest {

    private Integer id;
    private String code;
    private String name;
    private String description;
    private Boolean isActive;
}
