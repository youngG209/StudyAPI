package com.youngg209.dto.student;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class StudentResponseDataDto {

    private List<StudentListResponseDto> students;

    @Builder
    public StudentResponseDataDto(List<StudentListResponseDto> students) {
        this.students = students;
    }


}
