package com.youngg209.dto.subject;

import com.youngg209.dto.student.StudentSaveRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectRequestDataDto {

    private SubjectSaveRequestDto subject;

    @Builder
    public SubjectRequestDataDto(SubjectSaveRequestDto subject) {
        this.subject = subject;
    }


}
