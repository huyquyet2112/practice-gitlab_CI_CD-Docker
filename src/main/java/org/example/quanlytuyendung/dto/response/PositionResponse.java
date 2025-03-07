package org.example.quanlytuyendung.dto.response;

import lombok.Data;

@Data
public class PositionResponse {
    private Integer id;
    private String name;

    public PositionResponse(Integer positionId) {
        this.id = positionId;
    }
}
