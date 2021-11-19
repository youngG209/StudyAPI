package com.youngg209.controller;

import com.youngg209.dto.ResultMessageDto;
import com.youngg209.dto.subject.SubjectListResponseDto;
import com.youngg209.dto.subject.SubjectRequestDataDto;
import com.youngg209.dto.subject.SubjectResponseDataDto;
import com.youngg209.dto.subject.SubjectSaveRequestDto;
import com.youngg209.service.SubjectService;
import com.youngg209.utils.Matches;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Api(tags = {"과목 API"})
@RequiredArgsConstructor
@RestController
public class SubjectApiController {
    private final SubjectService subjectService;

    @ApiOperation(value = "과목 추가")
    @PostMapping("/subjects")
    public ResponseEntity<ResultMessageDto> save(@RequestBody SubjectRequestDataDto requestDto) {
        Matches matches = new Matches();
        SubjectSaveRequestDto subject = requestDto.getSubject();
        matches.isSubjectMatches(subject);
        subjectService.save(subject);
        return ResponseEntity.created(URI.create("/subjects")).body(new ResultMessageDto(null, null));
    }

    @ApiOperation(value = "과목 리스트", response = ResultMessageDto.class)
    @GetMapping("/subjects")
    public ResponseEntity<ResultMessageDto> findSubjects() {
        List<SubjectListResponseDto> subject = subjectService.findAll();
        SubjectResponseDataDto data = new SubjectResponseDataDto(subject);
        return ResponseEntity.ok(new ResultMessageDto(data, null));
    }

    @ApiOperation(value = "과목 삭제")
    @DeleteMapping("/subjects/{subjectId}")
    public ResponseEntity<ResultMessageDto> deleteSubject(@PathVariable Long subjectId) {
        subjectService.delete(subjectId);

        return ResponseEntity.noContent().build();
    }
}
