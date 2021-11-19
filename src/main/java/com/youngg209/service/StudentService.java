package com.youngg209.service;

import com.youngg209.domain.processScore.ProcessScore;
import com.youngg209.domain.processScore.ProcessScoreRepository;
import com.youngg209.domain.students.Student;
import com.youngg209.domain.students.StudentRepository;
import com.youngg209.domain.subjects.Subject;
import com.youngg209.domain.subjects.SubjectRepository;
import com.youngg209.dto.student.StudentListResponseDto;
import com.youngg209.dto.student.StudentSaveRequestDto;
import com.youngg209.exception.StudentExistException;
import com.youngg209.exception.StudentNonExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    private final ProcessScoreRepository processScoreRepository;

    private final ProcessScoreService processScoreService;

    @Transactional
    public Student save(StudentSaveRequestDto requestDto) {
        Student student = requestDto.toEntity();
        ProcessScore processScore = new ProcessScore();
        String phoneNumber = requestDto.getPhoneNumber();

        int existPhone = studentRepository.countByPhoneNumber(phoneNumber);
        if (existPhone > 0){
            throw new StudentExistException(phoneNumber);
        }

        List<Subject> subjects = subjectRepository.findAll();

        if (subjects.size() > 0) {
            for (Subject subject : subjects) {
                processScore = ProcessScore.builder()
                        .score(-1)
                        .subject(subject)
                        .student(student)
                        .build();
                processScoreRepository.save(processScore);
            }
        }
        Student students = studentRepository.save(student);


        return students;
    }

    @Transactional(readOnly = true)
    public List<StudentListResponseDto> findAll() {
        return studentRepository.findAll().stream()
                .map(StudentListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNonExistException(id));
        if (student != null) {
            processScoreService.deleteByStudent(student.getStudentId());
        }

        studentRepository.deleteById(student.getStudentId());
    }

}
