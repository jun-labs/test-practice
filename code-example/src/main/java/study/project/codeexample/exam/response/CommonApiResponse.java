package study.project.codeexample.exam.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonApiResponse<T> extends ResponseEntity<T> {

    private static final String SUCCESS = "success";
    private static final String DATA_NULL = "데이터가 존재하지 않습니다.";

    public CommonApiResponse() {
        super(HttpStatus.OK);
    }

    public String getSuccess() {
        return SUCCESS;
    }

    public String getDataNullMessage() {
        return DATA_NULL;
    }
}
