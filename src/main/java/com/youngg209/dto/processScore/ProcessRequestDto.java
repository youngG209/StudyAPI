package com.youngg209.dto.processScore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProcessRequestDto {

    private int score;
    private Long studentId;
    private Long subjectId;

    @Builder
    public ProcessRequestDto(int score, Long studentId, Long subjectId) {
        this.score = score;
        this.studentId = studentId;
        this.subjectId = subjectId;
    }
}
