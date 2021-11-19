package com.youngg209.dto.student;

import com.youngg209.domain.students.Student;
import lombok.Getter;

@Getter
public class StudentListResponseDto {
    private Long id;
    private String name;
    private int age;
    private String schoolType;
    private String phoneNumber;

    public StudentListResponseDto(Student entity) {
        this.id = entity.getStudentId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.schoolType = entity.getSchoolType();
        this.phoneNumber = entity.getPhoneNumber();
    }

    public StudentListResponseDto(Long id, String name, int age, String schoolType, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.schoolType = schoolType;
        this.phoneNumber = phoneNumber;
    }
}
