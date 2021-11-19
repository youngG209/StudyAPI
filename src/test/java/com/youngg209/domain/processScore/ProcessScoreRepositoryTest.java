package com.youngg209.domain.processScore;

import com.youngg209.domain.students.Student;
import com.youngg209.domain.students.StudentRepository;
import com.youngg209.domain.subjects.Subject;
import com.youngg209.domain.subjects.SubjectRepository;
import com.youngg209.utils.SchoolType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProcessScoreRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ProcessScoreRepository processScoreRepository;

    @BeforeEach
    public void before() {
        Student student = new Student("name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject("name");
        studentRepository.save(student);
        subjectRepository.save(subject);
    }
    @Test
    void findByStudentIdAndSubjectId() {
        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(80).build();

        processScoreRepository.save(processScore);

        ProcessScore byStudentIdAndSubjectId = processScoreRepository.findByStudentIdAndSubjectId(studentId, subjectId);

        assertThat(byStudentIdAndSubjectId.getScore()).isEqualTo(80);

    }
}