package com.example.serverconstruction.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html

    @Autowired
    private MockMvc mockMvc;

    @Before // 테스트 하기전 항상 먼저 실행되는 코드
    public void before() {
  	    mockMvc = MockMvcBuilders
                            .standaloneSetup(MainController.class)  // 테스트 대상 Controller 를 넣어준다.
          	                .alwaysExpect(MockMvcResultMatchers.status().isOk()) // 특정 필수 조건을 지정 
           	                .build();
    }

    @DisplayName("index.jsp view를 보여주는지 테스트")
    @Test
    public void mainPageTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
                
    }
}
