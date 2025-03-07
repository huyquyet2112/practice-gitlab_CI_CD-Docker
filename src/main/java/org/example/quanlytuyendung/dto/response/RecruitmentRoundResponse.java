package org.example.quanlytuyendung.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentRoundResponse {
    private Integer id;
    private RecruitmentRoundResponse recruitmentRoundType;
    private String name;
    private String code;
    private String description;
    private Boolean isActive;

    public RecruitmentRoundResponse(Integer id) {
        this.id = id;
    }

    public void setRecruitmentRoundType(Integer id, String name) {
        this.recruitmentRoundType = new RecruitmentRoundResponse();
        recruitmentRoundType.setId(id);
        recruitmentRoundType.setName(name);
    }
}
