package study.test.practice.common.login.outter;

import study.test.practice.common.login.presentation.dto.response.LoginResponse;

public interface LoginApi {

    LoginResponse callBackData(String valueA, String valueB);
}
