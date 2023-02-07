package study.test.practice.common.login.outter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import study.test.practice.common.login.presentation.dto.response.LoginResponse;

@Component
@RequiredArgsConstructor
public class GithubApi implements LoginApi {

    private final WebClient webClient;

    @Override
    public LoginResponse callBackData(String date, String hour) {
        try {
            return webClient.get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(LoginResponse.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
