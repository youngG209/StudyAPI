package com.youngg209.service;

import com.youngg209.domain.processScore.ProcessScore;
import com.youngg209.domain.processScore.ProcessScoreRepository;
import com.youngg209.domain.students.StudentRepository;
import com.youngg209.domain.subjects.SubjectRepository;
import com.youngg209.dto.processScore.ProcessRequestDto;
import com.youngg209.dto.processScore.ScoreStudentListResponseDto;
import com.youngg209.dto.processScore.ScoreSubjectListResponseDto;
import com.youngg209.exception.CommonBaseException;
import com.youngg209.utils.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProcessScoreService {

    private final ProcessScoreRepository processScoreRepository;

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    @Transactional
    public ProcessRequestDto assignScore(ProcessRequestDto processRequestDto, Long studentId, Long subjectId) {
        int score = processRequestDto.getScore();

        studentRepository.findById(studentId)
                .orElseThrow(() -> new CommonBaseException(ErrorStatus.STUNF, String.valueOf(studentId)));

        subjectRepository.findById(subjectId)
                .orElseThrow(() -> new CommonBaseException(ErrorStatus.SUBNF, String.valueOf(subjectId)));

        ProcessScore processScore = processScoreRepository.findByStudentIdAndSubjectId(studentId, subjectId);

        if (score > 0 && score <= 100) {
            processScore.update(score);
        }else {
            throw new CommonBaseException(ErrorStatus.CHECK_SCORE, String.valueOf(score));
        }

        return ProcessRequestDto.builder().score(processScore.getScore()).studentId(processScore.getStudent().getStudentId()).subjectId(processScore.getSubject().getSubjectId()).build();
    }

    @Transactional
    public void deleteByScore(Long studentId, Long subjectId) {


        studentRepository.findById(studentId)
                .orElseThrow(() -> new CommonBaseException(ErrorStatus.STUNF, String.valueOf(studentId)));

        subjectRepository.findById(subjectId)
                .orElseThrow(() -> new CommonBaseException(ErrorStatus.SUBNF, String.valueOf(subjectId)));

        ProcessScore processScore = processScoreRepository.findByStudentIdAndSubjectId(studentId, subjectId);
        processScore.update(-1);

    }

    @Transactional
    public void deleteByStudent(Long id) {

        List<ProcessScore> list = processScoreRepository.findByStudentId(id);
        if (list.size() > 0) {
            processScoreRepository.deleteByStudent(id);
        }

    }

    @Transactional
    public void deleteBySubject(Long id) {

        List<ProcessScore> list = processScoreRepository.findBySubjectId(id);
        if (list.size() > 0) {
            processScoreRepository.deleteBySubject(id);
        }

    }

    @Transactional(readOnly = true)
    public List<ScoreSubjectListResponseDto> findAllByStudent(Long studentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() -> new CommonBaseException(ErrorStatus.STUNF, String.valueOf(studentId)));

        return processScoreRepository.findByStudentId(studentId).stream()
                .map(ScoreSubjectListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ScoreStudentListResponseDto> findAllBySubject(Long subjectId) {

        subjectRepository.findById(subjectId)
                .orElseThrow(() -> new CommonBaseException(ErrorStatus.SUBNF, String.valueOf(subjectId)));

        return processScoreRepository.findBySubjectId(subjectId).stream()
                .map(ScoreStudentListResponseDto::new)
                .collect(Collectors.toList());
    }
}
