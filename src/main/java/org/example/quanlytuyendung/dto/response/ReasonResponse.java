package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.quanlytuyendung.entity.GroupReasonEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReasonResponse {
    private int id;
    private GroupReasonResponse groupReason;
    private String name;
    private Boolean isActive;
    private String description;

    public ReasonResponse(int id) {
        this.id = id;
    }
}
