package com.youngg209.dto.student;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentRequestDataDto {

    private StudentSaveRequestDto student;

    @Builder
    public StudentRequestDataDto(StudentSaveRequestDto student) {
        this.student = student;
    }


}
