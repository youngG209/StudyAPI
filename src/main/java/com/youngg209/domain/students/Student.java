package com.youngg209.domain.students;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private long studentId;

    @NotBlank(message = "[Request] 학생 이름은 Null 일 수 없습니다.")
    @Column(nullable = false)
    private String name;

    @Min(value = 1, message = "[Request] 학생 나이는 1 이상입니다.")
    @Column(nullable = false)
    private int age;

    @NotBlank(message = "[Request] 학생 이름은 Null 일 수 없습니다.")
    @Column(nullable = false)
    private String schoolType;

    @NotBlank(message = "[Request] 학생 연락처는 Null 일 수 없습니다.")
    @Column(nullable = false)
    private String phoneNumber;

    @Builder
    public Student(String name, int age, String schoolType, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.schoolType = schoolType;
        this.phoneNumber = phoneNumber;
    }

    public Student(Long studentId, String name, int age, String schoolType, String phoneNumber) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.schoolType = schoolType;
        this.phoneNumber = phoneNumber;
    }
}
