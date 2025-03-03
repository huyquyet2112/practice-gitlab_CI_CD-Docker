package org.example.quanlytuyendung.dto.request;

import lombok.Data;

import java.util.Set;
@Data
public class Line {
    private JobPositionRequest jobPosition;
    private Set<PositionRequest> positions;
}
