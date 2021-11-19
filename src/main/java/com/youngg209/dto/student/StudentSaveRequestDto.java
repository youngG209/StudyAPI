package com.youngg209.dto.student;

import com.youngg209.domain.students.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentSaveRequestDto {

    private String name;

    private int age;

    private String schoolType;

    private String phoneNumber;

    @Builder
    public StudentSaveRequestDto(String name, int age, String schoolType, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.schoolType = schoolType;
        this.phoneNumber = phoneNumber;
    }

    public Student toEntity() {
        return Student.builder()
                .name(name)
                .age(age)
                .schoolType(schoolType)
                .phoneNumber(phoneNumber)
                .build();
    }
}
