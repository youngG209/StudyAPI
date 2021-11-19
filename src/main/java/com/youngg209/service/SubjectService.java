package com.youngg209.service;

import com.youngg209.domain.processScore.ProcessScore;
import com.youngg209.domain.processScore.ProcessScoreRepository;
import com.youngg209.domain.students.Student;
import com.youngg209.domain.students.StudentRepository;
import com.youngg209.domain.subjects.Subject;
import com.youngg209.domain.subjects.SubjectRepository;
import com.youngg209.dto.subject.SubjectListResponseDto;
import com.youngg209.dto.subject.SubjectSaveRequestDto;
import com.youngg209.exception.SubjectExistException;
import com.youngg209.exception.SubjectNonExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubjectService {

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    private final ProcessScoreRepository processScoreRepository;

    private final ProcessScoreService processScoreService;

    @Transactional
    public Subject save(SubjectSaveRequestDto requestDto) {
        Subject subject = requestDto.toEntity();
        String name = requestDto.getName();
        ProcessScore processScore = new ProcessScore();

        int existName = subjectRepository.countByName(name);
        if (existName > 0){
            throw new SubjectExistException(name);
        }
        List<Student> students = studentRepository.findAll();
        if (students.size() > 0) {
            for (Student student : students) {
                processScore = ProcessScore.builder()
                        .score(-1)
                        .subject(subject)
                        .student(student)
                        .build();
                processScoreRepository.save(processScore);
            }
        }
        return subjectRepository.save(subject);
    }

    @Transactional(readOnly = true)
    public List<SubjectListResponseDto> findAll() {
        return subjectRepository.findAll().stream()
                .map(SubjectListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNonExistException(id));

        if (subject != null) {
            processScoreService.deleteBySubject(subject.getSubjectId());
        }
        subjectRepository.delete(subject);
    }

}
