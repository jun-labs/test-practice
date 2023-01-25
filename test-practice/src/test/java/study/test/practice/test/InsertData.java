package study.test.practice.test;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static study.test.practice.test.InsertData.MEMBER2_SQL_SCRIPT_PATH;
import static study.test.practice.test.InsertData.MEMBER_SQL_SCRIPT_PATH;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = MEMBER_SQL_SCRIPT_PATH),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = MEMBER2_SQL_SCRIPT_PATH)
})
public @interface InsertData {

    String MEMBER_SQL_SCRIPT_PATH = "classpath:/sql/user.sql";
    String MEMBER2_SQL_SCRIPT_PATH = "classpath:/sql/user2.sql";
}
