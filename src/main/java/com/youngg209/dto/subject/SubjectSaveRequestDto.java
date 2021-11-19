package com.youngg209.dto.subject;

import com.youngg209.domain.students.Student;
import com.youngg209.domain.subjects.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectSaveRequestDto {

    private String name;

    @Builder
    public SubjectSaveRequestDto(String name) {
        this.name = name;
    }

    public Subject toEntity() {
        return Subject.builder()
                .name(name)
                .build();
    }
}
