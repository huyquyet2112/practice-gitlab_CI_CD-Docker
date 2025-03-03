package org.example.quanlytuyendung.dto.response;

import lombok.*;

import java.util.Collections;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageableResponse<T> {
    @Builder.Default
    private List<T> content= Collections.emptyList();
    private int page;
    private int size;
    private String sort;
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
}
