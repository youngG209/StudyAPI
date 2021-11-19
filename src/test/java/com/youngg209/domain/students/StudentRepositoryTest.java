package com.youngg209.domain.students;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void countByPhoneNumber() {

        Student save = studentRepository.save(build());

        int i = studentRepository.countByPhoneNumber(save.getPhoneNumber());

        assertThat(i).isEqualTo(1);
    }

    @Test
    void findAll() {
        studentRepository.save(build());
        studentRepository.save(build());

        //when
        List<Student> postsList = studentRepository.findAll();

        for (Student student : postsList) {

            System.out.println(student.getStudentId());

        }

    }

    private Student build() {
            return Student.builder()
                    .name("d나12")
                    .age(18)
                    .schoolType("HIGH")
                    .phoneNumber("010-0000-0000")
                    .build();
        }
}