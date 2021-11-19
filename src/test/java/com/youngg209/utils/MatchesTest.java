package com.youngg209.utils;

import com.youngg209.dto.student.StudentSaveRequestDto;
import com.youngg209.dto.subject.SubjectSaveRequestDto;
import com.youngg209.exception.NotMatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class MatchesTest {
    private Matches matches;
    @BeforeEach
    void setUp() {
        matches = new Matches();
    }

    @Test
    void isStudentMatches() {

        String name = "test";
        int age = 1;
        String schoolType = "MIDDLE";
        String phoneNumber = "000-0000-0000";

        StudentSaveRequestDto requestDto = StudentSaveRequestDto.builder()
                .name(name)
                .age(age)
                .schoolType(schoolType)
                .phoneNumber(phoneNumber)
                .build();

        boolean matches = this.matches.isStudentMatches(requestDto);

        assertThat(matches).isTrue();

    }

    @Test
    void isStudentMatches_이름형식() {

        String name = "t#est";
        int age = 1;
        String schoolType = "MIDDLE";
        String phoneNumber = "000-0000-0000";

        StudentSaveRequestDto requestDto = StudentSaveRequestDto.builder()
                .name(name)
                .age(age)
                .schoolType(schoolType)
                .phoneNumber(phoneNumber)
                .build();

//        matches.isStudentMatches(requestDto);

        NotMatchException notMatchException = assertThrows(
                NotMatchException.class,
                () -> matches.isStudentMatches(requestDto)
        );
        System.out.println(notMatchException.getMatchVal());
        assertThat(notMatchException.getMatchVal()).isEqualTo(name);


    }

    @Test
    void isStudentMatches_이름길이() {

        String name = "test1111111111111111111111111111111111";
        int age = 1;
        String schoolType = "MIDDLE";
        String phoneNumber = "000-0000-0000";

        StudentSaveRequestDto requestDto = StudentSaveRequestDto.builder()
                .name(name)
                .age(age)
                .schoolType(schoolType)
                .phoneNumber(phoneNumber)
                .build();

        NotMatchException notMatchException = assertThrows(
                NotMatchException.class,
                () -> matches.isStudentMatches(requestDto)
        );
        System.out.println(notMatchException.getMatchVal());
        assertThat(notMatchException.getMatchVal()).isEqualTo(name);


    }

    @Test
    void isSubjectMatches_과목이름형식() {

        String name = "tes#t";

        SubjectSaveRequestDto requestDto = SubjectSaveRequestDto.builder()
                .name(name)
                .build();

        NotMatchException notMatchException = assertThrows(
                NotMatchException.class,
                () -> matches.isSubjectMatches(requestDto)
        );

        System.out.println(notMatchException.getMatchVal());
        assertThat(notMatchException.getMatchVal()).isEqualTo(name);
    }

    @Test
    void isSubjectMatches_과목이름길이() {

        String name = "test1111111111111111111111111";

        SubjectSaveRequestDto requestDto = SubjectSaveRequestDto.builder()
                .name(name)
                .build();

        NotMatchException notMatchException = assertThrows(
                NotMatchException.class,
                () -> matches.isSubjectMatches(requestDto)
        );

        System.out.println(notMatchException.getMatchVal());
        assertThat(notMatchException.getMatchVal()).isEqualTo(name);
    }
}