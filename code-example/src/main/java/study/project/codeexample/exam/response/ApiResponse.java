package study.project.codeexample.exam.response;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Locale;

public final class ApiResponse<T> {

    private final HttpStatus httpStatus;
    private String message;
    private int code;
    private T data;
    private Locale lang;
    private String platForm;
    private final LocalDateTime eventTime = LocalDateTime.now();

    public ApiResponse(T data, HttpServletRequest httpServletRequest) {
        this.httpStatus = HttpStatus.OK;
        this.message = "success";
        this.code = HttpStatus.OK.value();
        this.data = data;
        this.lang = httpServletRequest.getLocale();
        this.platForm = "web";
    }

    public ApiResponse() {
        this.httpStatus = HttpStatus.OK;
        this.message = "success";
        this.code = HttpStatus.OK.value();
        this.data = (T) "no data";
    }

    public ApiResponse(HttpServletRequest httpServletRequest) {
        this.httpStatus = HttpStatus.OK;
        this.message = "success";
        this.code = HttpStatus.OK.value();
        this.data = (T) "no data";
        this.lang = httpServletRequest.getLocale();
    }

    public static <T> ApiResponse<T> of() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> of(T data,
                                        HttpServletRequest httpServletRequest) {
        return new ApiResponse<>(
                data,
                httpServletRequest
        );
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public Locale getLang() {
        return lang;
    }

    public String getPlatForm() {
        return platForm;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
