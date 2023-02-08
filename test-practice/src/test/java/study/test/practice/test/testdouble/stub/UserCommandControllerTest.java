package study.test.practice.test.testdouble.stub;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import study.test.practice.domain.user.entity.User;
import study.test.practice.web.user.application.UserService;
import study.test.practice.web.user.presentation.UserController;
import study.test.practice.web.user.presentation.dto.request.UserSignupRequest;
import study.test.practice.web.user.presentation.dto.response.UserSignupResponse;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Mocking을 하게 되면 원하는 결과 값이 반환된다.")
    void Mocking_Gives_Wanted_Result() throws Exception {
        // given
        UserSignupRequest request = new UserSignupRequest("devjun");
        User newUser = new User("devjun");
        User result = new User(1L, "devjun");
        UserSignupResponse response = new UserSignupResponse(result);

        doReturn(result)
                .when(userService).save(newUser);

        // when
        ResultActions resultActions = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("userId", response.getUserId()).exists())
                .andDo(print());
    }
}
