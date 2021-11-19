package com.youngg209.controller;

import com.youngg209.dto.ResultMessageDto;
import com.youngg209.dto.processScore.*;
import com.youngg209.service.ProcessScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Api(tags = {"특정 학생, 특정 과목에 대한 API"})
@RequiredArgsConstructor
@RestController
public class ProcessScoreApiController {

    private final ProcessScoreService processScoreService;

    @ApiOperation(value = "특정 학생, 특정 과목 점수 추가")
    @PostMapping("/students/{studentId}/subjects/{subjectId}/scores")
    public ResponseEntity<ResultMessageDto> assignScore(@RequestBody ProcessRequestDto processRequestDto, @PathVariable Long studentId, @PathVariable Long subjectId) {

        ProcessRequestDto result = processScoreService.assignScore(processRequestDto, studentId, subjectId);

        return ResponseEntity.created(URI.create("/students/"+result.getStudentId()+"/subjects/"+result.getSubjectId()+"/scores")).body(new ResultMessageDto(null, null));
    }

    @ApiOperation(value = "특정 학생, 특정 과목 점수 수정")
    @PutMapping("/students/{studentId}/subjects/{subjectId}/scores")
    public ResponseEntity<ResultMessageDto> updateScore(@RequestBody ProcessRequestDto processRequestDto, @PathVariable Long studentId, @PathVariable Long subjectId) {

        processScoreService.assignScore(processRequestDto, studentId, subjectId);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "특정 학생, 특정 과목 점수 삭제")
    @DeleteMapping("/students/{studentId}/subjects/{subjectId}/scores")
    public ResponseEntity<ResultMessageDto> deleteScore(@PathVariable Long studentId, @PathVariable Long subjectId) {

        processScoreService.deleteByScore(studentId, subjectId);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "특정 학생의 평균 점수 조회", response = ResultMessageDto.class)
    @GetMapping("/students/{studentId}/average-score")
    public ResponseEntity<ResultMessageDto> getByStudentAverageScore(@PathVariable Long studentId) {
        double averageScore = 0;
        int totalScore = 0;

        List<ScoreSubjectListResponseDto> allByStudent = processScoreService.findAllByStudent(studentId);
        if (allByStudent.size() > 0) {
            for (ScoreSubjectListResponseDto dto : allByStudent) {
                if (dto.getScore() > -1) {
                    totalScore += dto.getScore();
                }
            }
        }
        if (totalScore == 0) {
            averageScore = -1;
        }else {
            averageScore = totalScore / allByStudent.size();
        }
        ProcessResultStudentDataDto data = new ProcessResultStudentDataDto(averageScore, allByStudent);
        return ResponseEntity.ok(new ResultMessageDto(data, null));
    }

    @ApiOperation(value = "특정 과목의 전체 학생 평균 점수 조회", response = ResultMessageDto.class)
    @GetMapping("/subjects/{subjectId}/average-score")
    public ResponseEntity<ResultMessageDto> getBySubjectAverageScore(@PathVariable Long subjectId) {
        double averageScore = 0;
        int totalScore = 0;

        List<ScoreStudentListResponseDto> allBySubject = processScoreService.findAllBySubject(subjectId);
        if (allBySubject.size() > 0) {
            for (ScoreStudentListResponseDto dto : allBySubject) {
                if (dto.getScore() > -1) {
                    totalScore += dto.getScore();
                }
            }
        }
        if (totalScore == 0) {
            averageScore = -1;
        }else {
            averageScore = totalScore / allBySubject.size();
        }
        ProcessResultSubjectDataDto data = new ProcessResultSubjectDataDto(averageScore, allBySubject);
        return ResponseEntity.ok(new ResultMessageDto(data, null));
    }
}
