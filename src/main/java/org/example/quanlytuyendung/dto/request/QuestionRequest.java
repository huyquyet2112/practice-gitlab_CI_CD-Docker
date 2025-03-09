package org.example.quanlytuyendung.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.quanlytuyendung.dto.response.GroupQuestionResponse;
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionRequest {
    private Integer id;
    private String name;
    private GroupReasonRequest groupQuestion;
    private String description;
    private Boolean isActive;
}
