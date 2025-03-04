package org.example.quanlytuyendung.dto.response;

import lombok.Data;

@Data
public class PositionResponse {
    private int id;
    private String name;

    public PositionResponse(int positionId) {
    }
}
