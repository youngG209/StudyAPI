package com.youngg209.controller;

import com.youngg209.dto.student.StudentRequestDataDto;
import com.youngg209.dto.student.StudentResponseDataDto;
import com.youngg209.dto.ResultMessageDto;
import com.youngg209.dto.student.StudentListResponseDto;
import com.youngg209.dto.student.StudentSaveRequestDto;
import com.youngg209.service.StudentService;
import com.youngg209.utils.Matches;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Api(tags = {"학생 API"})
@RequiredArgsConstructor
@RestController
public class StudentApiController {

    private final StudentService studentService;

    @ApiOperation(value = "학생 추가")
    @PostMapping("/students")
    public ResponseEntity<ResultMessageDto> save(@RequestBody StudentRequestDataDto requestDto) {
        Matches matches = new Matches();
        StudentSaveRequestDto student = requestDto.getStudent();
        boolean result = matches.isStudentMatches(student);
        if (result){
            studentService.save(student);
        }
        return ResponseEntity.created(URI.create("/students")).body(new ResultMessageDto(null, null));
    }

    @ApiOperation(value = "학생 리스트", response = ResultMessageDto.class)
    @GetMapping("/students")
    public ResponseEntity<ResultMessageDto> findStudents() {
        List<StudentListResponseDto> students = studentService.findAll();
        StudentResponseDataDto data = new StudentResponseDataDto(students);
        return ResponseEntity.ok(new ResultMessageDto(data, null));
    }

    @ApiOperation(value = "학생 삭제")
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<ResultMessageDto> delete(@PathVariable Long studentId) {
        studentService.delete(studentId);

        return ResponseEntity.noContent().build();
    }
}
