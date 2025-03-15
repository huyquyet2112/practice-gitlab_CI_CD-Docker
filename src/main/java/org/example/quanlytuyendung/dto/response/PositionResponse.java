package org.example.quanlytuyendung.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PositionResponse {
    private Integer id;
    private String name;

    public PositionResponse(Integer positionId) {
        this.id = positionId;
    }
}
