package study.test.practice.domain.count.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class WeatherCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countId;

    @Column
    private int count;

    public Long getCountId() {
        return countId;
    }

    public int getCount() {
        return count;
    }

    public void increase() {
        this.count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherCount that)) return false;
        return countId.equals(that.countId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countId);
    }

    @Override
    public String toString() {
        return countId.toString();
    }

}
