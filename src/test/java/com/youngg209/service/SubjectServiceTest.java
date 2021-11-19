package com.youngg209.service;

import com.youngg209.domain.processScore.ProcessScoreRepository;
import com.youngg209.domain.students.StudentRepository;
import com.youngg209.domain.subjects.Subject;
import com.youngg209.domain.subjects.SubjectRepository;
import com.youngg209.dto.subject.SubjectSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ProcessScoreRepository processScoreRepository;

    @Test
    void save() {
        //given
        final Subject subject = Subject.builder().name("test").build();
        final SubjectSaveRequestDto subjectSaveRequestDto = SubjectSaveRequestDto.builder().name(subject.getName()).build();

        given(subjectRepository.save(any())).willReturn(subject);

        //when
        final Subject subjectResult = subjectService.save(subjectSaveRequestDto);

        //then
        assertThat(subjectResult.getName()).isEqualTo(subject.getName());

        // verify
        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    void findAll() {
        // given
        doReturn(subjects()).when(subjectRepository).findAll();

        //when
        List<Subject> list = subjectRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(5);
    }

    private List<Subject> subjects() {
        final List<Subject> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Subject((long) i, "test" + i));
        }
        return list;
    }

    @Test
    void delete() {
        final Subject subject = new Subject(1L, "test");

        subjectRepository.deleteById(subject.getSubjectId());
        verify(subjectRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void delete_과목없음() throws Exception {
        final Subject subject = new Subject(1L, "test");
        long subjectId = subject.getSubjectId();

        assertThat(subject.getName()).isEqualTo("test");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    subjectService.delete(subjectId);
                }).withMessageContaining("해당 과목이 없습니다.");

    }
}