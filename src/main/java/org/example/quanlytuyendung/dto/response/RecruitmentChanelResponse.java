package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.quanlytuyendung.entity.CandicateSourceEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentChanelResponse {
    private int id;
    private CandicateSourceResponse candicateSource;
    private String name;
    private Boolean isActive;
    private String description;

    public RecruitmentChanelResponse(int id) {
        this.id = id;
    }
}
