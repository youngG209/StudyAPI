package com.youngg209.domain.subjects;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBJECT_ID")
    private long subjectId;

    @NotBlank(message = "[Request] 과목의 이름은 Null 일 수 없습니다.")
    @Column(nullable = false)
    private String name;

    @Builder
    public Subject(String name) {
        this.name = name;
    }

    public Subject(Long subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }
}
