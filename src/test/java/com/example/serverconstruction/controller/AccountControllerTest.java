package com.example.serverconstruction.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.serverconstruction.repository.AccountRepository;

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
public class AccountControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Before // 테스트 하기전 항상 먼저 실행되는 코드
    public void before() {
  	    mockMvc = MockMvcBuilders
                            .standaloneSetup(AccountController.class)  // 테스트 대상 Controller 를 넣어준다.
          	                .alwaysExpect(MockMvcResultMatchers.status().isOk()) // 특정 필수 조건을 지정 
           	                .build();
    }

    // @DisplayName("회원 가입 처리 - 입력값 오류 (추후 vaildation설정한다면)")
    // @Test
    // public void signUpSubmit_with_uncorrect_input() throws Exception{
    //     mockMvc.perform(MockMvcRequestBuilders.post("/api/account/sign-up")
    //         .param("name", "test")
    //         .param("email", "test@test.com")
    //         .param("password", "12345")
    //         .param("gender", "male")
    //         .param("authenticated", ))
    //             .andExpect(MockMvcResultMatchers.status().isOk());

    // }

    
    @DisplayName("회원 가입 처리 - 입력값 오류 (추후 vaildation설정한다면)")
    @Test
    public void signUpSubmit_with_correct_input() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/boot/account/sign-up")
            .param("name", "test")
            .param("email", "test@test.com")
            .param("password", "12345")
            .param("gender", "male"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        assertTrue(accountRepository.existsByEmail("test@test.com"));
    }
}
