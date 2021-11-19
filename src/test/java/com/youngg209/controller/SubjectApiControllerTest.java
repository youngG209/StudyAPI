package com.youngg209.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.youngg209.domain.subjects.Subject;
import com.youngg209.domain.subjects.SubjectRepository;
import com.youngg209.dto.ResultMessageDto;
import com.youngg209.dto.student.StudentListResponseDto;
import com.youngg209.dto.subject.SubjectRequestDataDto;
import com.youngg209.dto.subject.SubjectSaveRequestDto;
import com.youngg209.service.SubjectService;
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
class SubjectApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @DisplayName("과목추가")
    @Test
    void save() throws Exception {
        //given
        String name = "aAzZ이ㅏㄱ10";

        SubjectRequestDataDto subjectRequestDataDto = getBuild(name);
        String dataDto = objectMapper.writeValueAsString(subjectRequestDataDto);

        //then
        MvcResult mvcResult = mockMvc.perform(
                        post("/subjects")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataDto)
                )
                .andDo(print())
                .andExpect(status().isCreated()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @DisplayName("과목 정규식 확인")
    @Test
    void save_과명조건확인() throws Exception {
        //given
        String name = "aAzZ@ㅏㄱ10";

        SubjectRequestDataDto subjectRequestDataDto = getBuild(name);
        String dataDto = objectMapper.writeValueAsString(subjectRequestDataDto);

        //then
        MvcResult mvcResult = mockMvc.perform(
                        post("/subjects")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataDto)
                )
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    @DisplayName("과목 길이 확인")
    @Test
    void save_과명길이확인() throws Exception {
        //given
        String name = "aAz11111111111111111111111ㅏㄱ10";

        SubjectRequestDataDto subjectRequestDataDto = getBuild(name);
        String dataDto = objectMapper.writeValueAsString(subjectRequestDataDto);

        //then
        MvcResult mvcResult = mockMvc.perform(
                        post("/subjects")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataDto)
                )
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isNotNull();
    }

    private SubjectRequestDataDto getBuild(String name) {
        return SubjectRequestDataDto.builder()
                .subject(SubjectSaveRequestDto.builder()
                                .name(name)
                                .build())
                .build()
                ;
    }

    @DisplayName("과목전체조회")
    @Test
    void findSubjects() throws Exception {

        //given
        students();

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/subjects")
        );

        // then
        final MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
        ResultMessageDto data = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ResultMessageDto.class);
        Map data1 = (Map) data.getData();
        List list = (List) data1.get("subjects");
        assertThat(list.size()).isEqualTo(5);

    }

    private List<StudentListResponseDto> students() {
        final List<StudentListResponseDto> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {

            subjectRepository.save((new Subject((long) i,"test"+i)));
        }
        return list;
    }

    @Test
    void delete() throws Exception {
        final Subject subject = new Subject(1L, "test");
        SubjectSaveRequestDto subjectSaveRequestDto = new SubjectSaveRequestDto(subject.getName());
        Subject save = subjectService.save(subjectSaveRequestDto);
        // when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/subjects/"+save.getSubjectId())
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isNoContent());

    }
}