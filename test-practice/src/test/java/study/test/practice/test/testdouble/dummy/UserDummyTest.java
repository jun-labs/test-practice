package study.test.practice.test.testdouble.dummy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.test.practice.domain.order.Order;

import java.math.BigDecimal;

@DisplayName("UserDummy 테스트")
class UserDummyTest {

    @Test
    @DisplayName("User 더미를 넣더라도 Order 객체가 생성된다.")
    void Order_Dummy_Test() {
        UserDummy dummy = new UserDummy();
        Order order = new Order(new BigDecimal(1000L), dummy);

        Assertions.assertNotNull(order);
    }
}
