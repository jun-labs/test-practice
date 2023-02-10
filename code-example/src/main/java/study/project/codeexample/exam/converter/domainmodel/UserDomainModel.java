package study.project.codeexample.exam.converter.domainmodel;

import java.util.Objects;

public class UserDomainModel {

    private final Long id;
    private final String name;
    private final Long version;

    public UserDomainModel(
            Long id,
            String name,
            Long version
    ) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDomainModel that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
