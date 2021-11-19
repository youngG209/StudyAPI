package com.youngg209.dto.processScore;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProcessResultStudentDataDto {

    private double averageScore;
    private List<ScoreSubjectListResponseDto> students;

    @Builder
    public ProcessResultStudentDataDto(double averageScore, List<ScoreSubjectListResponseDto> students) {
        this.averageScore = averageScore;
        this.students = students;
    }


}
