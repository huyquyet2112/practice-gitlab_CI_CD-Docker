package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.quanlytuyendung.entity.GroupQuestionEntity;
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionResponse {
    private Integer id;
    private String name;
    private GroupQuestionResponse groupQuestion;
    private String description;
    private Boolean isActive;

    public QuestionResponse(Integer id) {
        this.id = id;
    }
}
