package com.youngg209.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngg209.domain.processScore.ProcessScore;
import com.youngg209.domain.processScore.ProcessScoreRepository;
import com.youngg209.domain.students.Student;
import com.youngg209.domain.students.StudentRepository;
import com.youngg209.domain.subjects.Subject;
import com.youngg209.domain.subjects.SubjectRepository;
import com.youngg209.dto.processScore.ProcessRequestDto;
import com.youngg209.utils.SchoolType;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProcessScoreApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ProcessScoreRepository processScoreRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeEach
    public void before() {
        Student student = new Student("name1", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject("name1");
        studentRepository.save(student);
        subjectRepository.save(subject);

        student = new Student("name2", 10, SchoolType.ELE.getCode(), "011-0000-0000");
        subject = new Subject("name2");
        studentRepository.save(student);
        subjectRepository.save(subject);
    }

    @Test
    void assignScore() throws Exception {
        //given
        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(80).build();

        processScoreRepository.save(processScore);

//        studentSubject = StudentSubject.builder().student(student).subject(subject).score(50).build();

        ProcessRequestDto processRequestDto = ProcessRequestDto.builder().studentId(studentId).subjectId(subjectId).score(50).build();
        String dataDto = objectMapper.writeValueAsString(processRequestDto);

        MvcResult mvcResult = mockMvc.perform(
                                post("/students/1/subjects/1/scores")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(dataDto)
                        )
                        .andDo(print())
                        .andExpect(status().isCreated()).andReturn();


        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @Test
    void assignScore_학생없음() throws Exception {
        //given
        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(80).build();

        processScoreRepository.save(processScore);

//        studentSubject = StudentSubject.builder().student(student).subject(subject).score(50).build();

        ProcessRequestDto processRequestDto = ProcessRequestDto.builder().studentId(studentId).subjectId(subjectId).score(50).build();
        String dataDto = objectMapper.writeValueAsString(processRequestDto);

        MvcResult mvcResult = mockMvc.perform(
                                post("/students/3/subjects/1/scores")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(dataDto)
                        )
                        .andDo(print())
                        .andExpect(status().isBadRequest()).andReturn();


        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @Test
    void assignScore_과목없음() throws Exception {
        //given
        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(80).build();

        processScoreRepository.save(processScore);

//        studentSubject = StudentSubject.builder().student(student).subject(subject).score(50).build();

        ProcessRequestDto processRequestDto = ProcessRequestDto.builder().studentId(studentId).subjectId(subjectId).score(50).build();
        String dataDto = objectMapper.writeValueAsString(processRequestDto);

        MvcResult mvcResult = mockMvc.perform(
                                post("/students/3/subjects/1/scores")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(dataDto)
                        )
                        .andDo(print())
                        .andExpect(status().isBadRequest()).andReturn();


        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @Test
    void updateScore() throws Exception {

        //given
        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(80).build();

        processScoreRepository.save(processScore);

//        studentSubject = StudentSubject.builder().student(student).subject(subject).score(50).build();

        ProcessRequestDto processRequestDto = ProcessRequestDto.builder().studentId(studentId).subjectId(subjectId).score(50).build();
        String dataDto = objectMapper.writeValueAsString(processRequestDto);

        MvcResult mvcResult = mockMvc.perform(
                                put("/students/1/subjects/1/scores")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(dataDto)
                        )
                        .andDo(print())
                        .andExpect(status().isNoContent()).andReturn();


        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @Test
    void deleteScore() throws Exception {

        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(80).build();

        processScoreRepository.save(processScore);

        // when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/students/" + studentId + "/subjects/" + subjectId + "/scores")
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    void deleteScore_학생없음() throws Exception {

        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(80).build();

        processScoreRepository.save(processScore);

        // when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/students/3/subjects/1/scores")
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void deleteScore_과목없음() throws Exception {

        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(80).build();

        processScoreRepository.save(processScore);

        // when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/students/1/subjects/3/scores")
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void getByStudentAverageScore() throws Exception {
        //given
        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(80).build();

        processScoreRepository.save(processScore);
        //given
        studentId = 1L;
        subjectId = 2L;
        student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        subject = new Subject(subjectId, "name");
        processScore = ProcessScore.builder().student(student).subject(subject).score(20).build();

        processScoreRepository.save(processScore);

//        studentSubject = StudentSubject.builder().student(student).subject(subject).score(50).build();

        ProcessRequestDto processRequestDto = ProcessRequestDto.builder().studentId(studentId).subjectId(subjectId).score(50).build();
        String dataDto = objectMapper.writeValueAsString(processRequestDto);

        MvcResult mvcResult = mockMvc.perform(
                                get("/students/1/average-score")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(dataDto)
                        )
                        .andDo(print())
                        .andExpect(status().isOk()).andReturn();


        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @Test
    void getByStudentAverageScore_학생없음() throws Exception {
        //given
        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(80).build();

        processScoreRepository.save(processScore);
        //given
        studentId = 1L;
        subjectId = 2L;
        student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        subject = new Subject(subjectId, "name");
        processScore = ProcessScore.builder().student(student).subject(subject).score(20).build();

        processScoreRepository.save(processScore);

//        studentSubject = StudentSubject.builder().student(student).subject(subject).score(50).build();

        ProcessRequestDto processRequestDto = ProcessRequestDto.builder().studentId(studentId).subjectId(subjectId).score(50).build();
        String dataDto = objectMapper.writeValueAsString(processRequestDto);

        MvcResult mvcResult = mockMvc.perform(
                                get("/students/3/average-score")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(dataDto)
                        )
                        .andDo(print())
                        .andExpect(status().isBadRequest()).andReturn();


        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @Test
    void getBySubjectAverageScore() throws Exception {
        //given
        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(90).build();

        processScoreRepository.save(processScore);
        //given
        studentId = 2L;
        subjectId = 1L;
        student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        subject = new Subject(subjectId, "name");
        processScore = ProcessScore.builder().student(student).subject(subject).score(20).build();

        processScoreRepository.save(processScore);

//        studentSubject = StudentSubject.builder().student(student).subject(subject).score(50).build();

        ProcessRequestDto processRequestDto = ProcessRequestDto.builder().studentId(studentId).subjectId(subjectId).score(50).build();
        String dataDto = objectMapper.writeValueAsString(processRequestDto);

        MvcResult mvcResult = mockMvc.perform(
                                get("/subjects/1/average-score")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(dataDto)
                        )
                        .andDo(print())
                        .andExpect(status().isOk()).andReturn();


        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @Test
    void getBySubjectAverageScore_과목없음() throws Exception {
        //given
        Long studentId = 1L;
        Long subjectId = 1L;
        Student student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        Subject subject = new Subject(subjectId, "name");
        ProcessScore processScore = ProcessScore.builder().student(student).subject(subject).score(90).build();

        processScoreRepository.save(processScore);
        //given
        studentId = 2L;
        subjectId = 1L;
        student = new Student(studentId, "name", 10, SchoolType.ELE.getCode(), "010-0000-0000");
        subject = new Subject(subjectId, "name");
        processScore = ProcessScore.builder().student(student).subject(subject).score(20).build();

        processScoreRepository.save(processScore);

//        studentSubject = StudentSubject.builder().student(student).subject(subject).score(50).build();

        ProcessRequestDto processRequestDto = ProcessRequestDto.builder().studentId(studentId).subjectId(subjectId).score(50).build();
        String dataDto = objectMapper.writeValueAsString(processRequestDto);

        MvcResult mvcResult = mockMvc.perform(
                                get("/subjects/3/average-score")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(dataDto)
                        )
                        .andDo(print())
                        .andExpect(status().isBadRequest()).andReturn();


        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }
}