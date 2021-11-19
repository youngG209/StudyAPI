package com.youngg209.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.youngg209.domain.students.Student;
import com.youngg209.domain.students.StudentRepository;
import com.youngg209.dto.ResultMessageDto;
import com.youngg209.dto.student.StudentListResponseDto;
import com.youngg209.dto.student.StudentRequestDataDto;
import com.youngg209.dto.student.StudentSaveRequestDto;
import com.youngg209.service.StudentService;
import com.youngg209.utils.SchoolType;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class StudentApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @DisplayName("학생추가")
    @Test
    void save() throws Exception {
        //given
        String name = "aAzZ이ㅏㄱ10";
        int age = 10;
        String schoolType = SchoolType.MID.getCode();
        String phoneNumber = "010-0000-0000";

        StudentRequestDataDto studentRequestDataDto = getBuild(name, age, schoolType, phoneNumber);
        String dataDto = objectMapper.writeValueAsString(studentRequestDataDto);

        //then
        MvcResult mvcResult = mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataDto)
                )
                .andExpect(status().isCreated()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @DisplayName("학생 빈값 확인")
    @Test
    void save_학생빈값확인() throws Exception {
        //given
        String name = "";
        int age = 10;
        String schoolType = SchoolType.MID.getCode();
        String phoneNumber = "010-0000-0000";

        StudentRequestDataDto studentRequestDataDto = getBuild(name, age, schoolType, phoneNumber);
        String dataDto = objectMapper.writeValueAsString(studentRequestDataDto);

        //when
        MvcResult mvcResult = mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataDto)
                )
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn();

        //then
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @DisplayName("학생 이름 정규식 확인")
    @Test
    void save_학생이름정규식확인() throws Exception {
        //given
        String name = "aAzZ이ㅏ*0";
        int age = 10;
        String schoolType = SchoolType.MID.getCode();
        String phoneNumber = "010-0000-0000";

        StudentRequestDataDto studentRequestDataDto = getBuild(name, age, schoolType, phoneNumber);
        String dataDto = objectMapper.writeValueAsString(studentRequestDataDto);

        //when
        MvcResult mvcResult = mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataDto)
                )
                .andExpect(status().isBadRequest()).andReturn();

        //then
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @DisplayName("학생 이름 길이 확인")
    @Test
    void save_학생이름길이확인() throws Exception {
        //given
        String name = "aAzZ이ㅏ111111111111111111111111111111";
        int age = 10;
        String schoolType = SchoolType.MID.getCode();
        String phoneNumber = "010-0000-0000";

        StudentRequestDataDto studentRequestDataDto = getBuild(name, age, schoolType, phoneNumber);
        String dataDto = objectMapper.writeValueAsString(studentRequestDataDto);

        //when
        MvcResult mvcResult = mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataDto)
                )
                .andExpect(status().isBadRequest()).andReturn();

        //then
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @DisplayName("학생 나이 값 확인")
    @Test
    void save_학생나이값확인() throws Exception {
        //given
        String name = "aAzZ이ㅏ11";
        int age = 20;
        String schoolType = SchoolType.MID.getCode();
        String phoneNumber = "010-0000-0000";

        StudentRequestDataDto studentRequestDataDto = getBuild(name, age, schoolType, phoneNumber);
        String dataDto = objectMapper.writeValueAsString(studentRequestDataDto);

        //when
        MvcResult mvcResult = mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataDto)
                )
                .andExpect(status().isBadRequest()).andReturn();

        //then
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @DisplayName("학생 학교급 값 확인")
    @Test
    void save_학생학교급값확인() throws Exception {
        //given
        String name = "aAzZ이ㅏ11";
        int age = 12;
        String schoolType = "ㅁㅁㅁ";
        String phoneNumber = "010-0000-0000";

        StudentRequestDataDto studentRequestDataDto = getBuild(name, age, schoolType, phoneNumber);
        String dataDto = objectMapper.writeValueAsString(studentRequestDataDto);

        //when
        MvcResult mvcResult = mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataDto)
                )
                .andExpect(status().isBadRequest()).andReturn();

        //then
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @DisplayName("학생 연락처 형식 확인")
    @Test
    void save_학생연락처형식확인() throws Exception {
        //given
        String name = "aAzZ이ㅏ11";
        int age = 12;
        String schoolType = SchoolType.MID.getCode();
        String phoneNumber = "010-0000-01000";

        StudentRequestDataDto studentRequestDataDto = getBuild(name, age, schoolType, phoneNumber);
        String dataDto = objectMapper.writeValueAsString(studentRequestDataDto);

        //when
        MvcResult mvcResult = mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataDto)
                )
                .andExpect(status().isBadRequest()).andReturn();

        //then
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    private StudentRequestDataDto getBuild(String name, int age, String schoolType, String phoneNumber) {
        return StudentRequestDataDto.builder()
                .student(StudentSaveRequestDto.builder()
                                .name(name)
                                .age(age)
                                .schoolType(schoolType)
                                .phoneNumber(phoneNumber)
                                .build())
                .build()
                ;
    }

    @DisplayName("학생전체조회")
    @Test
    void findStudents() throws Exception {

        //given
        students();

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/students")
        );

        // then
        final MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
        ResultMessageDto data = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ResultMessageDto.class);
        Map data1 = (Map) data.getData();
        List list = (List) data1.get("students");
        assertThat(list.size()).isEqualTo(5);

    }

    private List<StudentListResponseDto> students() {
        final List<StudentListResponseDto> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {

            studentRepository.save((new Student((long) i,"test"+i, 10, SchoolType.HIGH.getCode(), "010-0000-0000")));
        }
        return list;
    }
}