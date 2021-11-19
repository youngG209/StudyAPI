package com.youngg209.dto.processScore;

import com.youngg209.domain.processScore.ProcessScore;
import lombok.Getter;

@Getter
public class ScoreStudentListResponseDto {
    private Long id;
    private String name;
    private int score;

    public ScoreStudentListResponseDto(ProcessScore entity) {
        this.id = entity.getId();
        this.name = entity.getStudent().getName();
        this.score = entity.getScore();
    }

    public ScoreStudentListResponseDto(Long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
