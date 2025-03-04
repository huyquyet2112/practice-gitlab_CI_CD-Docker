package org.example.quanlytuyendung.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.quanlytuyendung.entity.IndustryEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobPositionRequest {
    private int id;
    private String code;
    private String name;
    private IndustryEntity industry;
    private String description;
    private List<LineRequest> lines;
    private Boolean isActive;
}
