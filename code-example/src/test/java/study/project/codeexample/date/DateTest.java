package study.project.codeexample.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.project.codeexample.data.date.DateTestData;

import java.time.LocalDate;

class DateTest {

    @Test
    @DisplayName("날짜 객체는 값으로 동일 비교한다.")
    void 날짜_객체_값_비교() throws Exception {
        LocalDate date = DateTestData.createCorrectExampleTestData(2023, 10, 23);
        Assertions.assertEquals(
                LocalDate.of(2023, 10, 23),
                date
        );
    }

    @Test
    @DisplayName("값이 같다고 같은 객체는 아니다.")
    void 동일성_테스트() throws Exception {
        LocalDate date = DateTestData.createCorrectExampleTestData(2023, 10, 23);
        Assertions.assertNotSame(LocalDate.of(2023, 10, 23), date);
    }

    @Test
    @DisplayName("당일 날짜에 따라 테스트가 참 또는 거짓이 될 수 있다.")
    void Ambient_Context_테스트() throws Exception {
        LocalDate date = DateTestData.createBadExampleTestData();
        Assertions.assertNotEquals(
                LocalDate.of(2023, 10, 23),
                date
        );
    }
}
