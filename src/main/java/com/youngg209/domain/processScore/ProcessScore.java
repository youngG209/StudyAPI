package com.youngg209.domain.processScore;

import com.youngg209.domain.students.Student;
import com.youngg209.domain.subjects.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
@Entity(name="STUDENT_SUBJECT")
public class ProcessScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subject;

    @Min(value = -1)
    @Max(value = 100, message = "[Request] 점수는 0~100까지만 가능합니다.")
    private int score;


    @Builder
    public ProcessScore(int score, Student student, Subject subject) {
        this.score = score;
        this.student = student;
        this.subject = subject;
    }

    public void update(int score) {
        this.score = score;
    }

}
