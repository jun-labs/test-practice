package study.project.codeexample.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Version
    private Long version;

    protected User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(
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

    public void update(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
