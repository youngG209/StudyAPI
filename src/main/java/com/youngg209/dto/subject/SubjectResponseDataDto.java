package com.youngg209.dto.subject;

import com.youngg209.dto.student.StudentListResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SubjectResponseDataDto {

    private List<SubjectListResponseDto> subjects;

    @Builder
    public SubjectResponseDataDto(List<SubjectListResponseDto> subjects) {
        this.subjects = subjects;
    }


}
