package study.project.codeexample.web.user.presentation.dto.response;

import study.project.codeexample.domain.user.entity.UserType;

import java.util.Objects;

public class UserInformationResponseV2 {

    private final Long id;
    private final String name;
    private final UserType memberType;

    public UserInformationResponseV2(Long id, String name) {
        this.id = id;
        this.name = name;
        this.memberType = getMemberType(name);
    }

    private UserType getMemberType(String name) {
        return Objects.isNull(name) ? UserType.UN_KNOWN : UserType.KNOWN;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserType getMemberType() {
        return memberType;
    }
}
