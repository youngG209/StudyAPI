package com.youngg209.dto.subject;

import com.youngg209.domain.students.Student;
import com.youngg209.domain.subjects.Subject;
import lombok.Getter;

@Getter
public class SubjectListResponseDto {
    private Long id;
    private String name;

    public SubjectListResponseDto(Subject entity) {
        this.id = entity.getSubjectId();
        this.name = entity.getName();
    }

    public SubjectListResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
