package com.youngg209.dto.processScore;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProcessResultSubjectDataDto {

    private double averageScore;
    private List<ScoreStudentListResponseDto> subjects;

    @Builder
    public ProcessResultSubjectDataDto(double averageScore, List<ScoreStudentListResponseDto> subjects) {
        this.averageScore = averageScore;
        this.subjects = subjects;
    }


}
