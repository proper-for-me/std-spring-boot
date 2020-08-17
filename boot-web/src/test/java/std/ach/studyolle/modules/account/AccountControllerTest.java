package std.ach.studyolle.modules.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//@ExtendWith(MockitoExtension.class)

//@SpringBootTest( classes = BootWebApp.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired AccountRepository accountRepository;
    @MockBean JavaMailSender javaMailSender;

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/signup"))
                .andDo(print())
        ;
    }

    @DisplayName("회원가입화면 호출 테스트")
    @Test
    void signUpForm() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/signup"))
                .andDo(print())
        ;
    }

    @DisplayName("회원가입화면 처리 - 입력값 오류")
    @Test
    void signUpSubmit_with_wrong_input() throws Exception {
        mockMvc.perform(post("/signup")
                .param("nickName", "user")
                .param("email", "abc@google.com")
                .param("password","1234")
                .with(csrf()) )
                .andExpect(status().isOk())
                .andExpect(view().name("account/signup"))
                .andDo(print())
        ;
    }

    @DisplayName("회원가입화면 처리 - 입력값 정상")
    @Test
    void signUpSubmit_with_correct_input() throws Exception {
        mockMvc.perform(post("/signup")
                .param("nickName", "user")
                .param("email", "abc...")
                .param("password","1234")
                .with(csrf()) )
                .andExpect(status().isOk())
                .andExpect(view().name("account/signup"))
                .andDo(print())
                ;

        assertNotNull(accountRepository.existsByEmail("keesun@email.com"));

        then(javaMailSender).should().send(any(SimpleMailMessage.class));
        ;
    }
}