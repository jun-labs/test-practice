package study.project.codeexample.domain.order.entity;

import study.project.codeexample.domain.user.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;


    protected Order() {
    }

    public Order(User user) {
        this.user = user;
        this.count = 0;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        this.count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getId().equals(order.getId());
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
