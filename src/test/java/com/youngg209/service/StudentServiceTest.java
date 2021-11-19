package com.youngg209.service;

import com.youngg209.domain.students.Student;
import com.youngg209.domain.students.StudentRepository;
import com.youngg209.dto.student.StudentSaveRequestDto;
import com.youngg209.utils.SchoolType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @DisplayName("학생추가")
    @Test
    void save() {
        //given
        final StudentSaveRequestDto studentSaveRequestDto = StudentSaveRequestDto.builder().build();

        //when
        doReturn(new Student(studentSaveRequestDto.getName(), studentSaveRequestDto.getAge(), studentSaveRequestDto.getSchoolType(), studentSaveRequestDto.getPhoneNumber()))
                .when(studentRepository).save(any(Student.class));
        final Student student = studentService.save(studentSaveRequestDto);

        //then
        assertThat(student.getName()).isEqualTo(studentSaveRequestDto.getName());
        assertThat(student.getPhoneNumber()).isEqualTo(studentSaveRequestDto.getPhoneNumber());

        // verify
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @DisplayName("학생전체조회")
    @Test
    void findAll() {
        // given
        doReturn(students()).when(studentRepository).findAll();

        //when
        List<Student> list = studentRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(5);
    }

    private List<Student> students() {
        final List<Student> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Student((long) i, "test" + i, 10, SchoolType.HIGH.getCode(), "010-0000-0000"));
        }
        return list;
    }

    @Test
    public void delete() {
        final Student student = new Student(1L, "test" + 1, 10, SchoolType.HIGH.getCode(), "010-0000-0000");

        studentRepository.deleteById(student.getStudentId());
        verify(studentRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void delete_학생없음() throws Exception {
        final Student student = new Student(1L, "test", 10, SchoolType.HIGH.getCode(), "010-0000-0000");
        long studentId = student.getStudentId();

        assertThat(student.getName()).isEqualTo("test");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    studentService.delete(studentId);
                }).withMessageContaining("해당 학생이 없습니다.");

    }
}