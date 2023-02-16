package study.project.codeexample.web.user.presentation.dto.response;

import java.util.Objects;

public class UserInformationResponse {

    private final Long id;
    private final String name;
    private final String memberType;

    public UserInformationResponse(Long id, String name) {
        this.id = id;
        this.name = name;
        this.memberType = getMemberType(name);
    }

    private String getMemberType(String name) {
        return Objects.isNull(name) ? "UN_KNOWN" : "KNOWN";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMemberType() {
        return memberType;
    }
}
