package study.project.codeexample.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import study.project.codeexample.domain.user.entity.User;
import study.project.codeexample.domain.user.infrastructure.UserJpaRepository;
import study.project.codeexample.exam.lock.application.OrderCommandService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class LockTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private OrderCommandService orderCommandService;

    private User user;

    @BeforeEach
    void beforeEach() {
        user = userJpaRepository.save(new User("helloV1"));
    }

    @Test
    @DisplayName("비관적 락(Read)이 걸린 상태에서 수정을 가하면 Deadlock이 발생해서 버전이 증가하지 않는다.")
    void PESSIMISTIC_READ_데드락_테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int index = 0; index < 5; index++) {
            final int value = index;
            executorService.submit(() -> {
                try {
                    orderCommandService.orderV2_1(user.getId(), "Change" + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        User findUser = userJpaRepository.findById(user.getId()).get();
        Assertions.assertNotEquals(5, findUser.getVersion());
    }

    @Test
    @DisplayName("비관적 락(Read)이 걸린 상태에서 수정이 없다면 Deadlock이 발생하지 않으며 버전이 증가하지 않는다.")
    void PESSIMISTIC_READ_테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int index = 0; index < 5; index++) {
            final int value = index;
            executorService.submit(() -> {
                try {
                    orderCommandService.orderV2_2(user.getId(), "Change" + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        User findUser = userJpaRepository.findById(user.getId()).get();
        Assertions.assertEquals(0, findUser.getVersion());
    }

    @Test
    @DisplayName("비관적 락(Write)이 걸린 상태에서는 데이터베이스에서 락을 관리하기 때문에 정상적으로 버전이 증가한다.")
    void PESSIMISTIC_WRITE_데드락_테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int index = 0; index < 10; index++) {
            final int value = index;
            executorService.submit(() -> {
                try {
                    orderCommandService.orderV3(user.getId(), "Change" + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        User findUser = userJpaRepository.findById(user.getId()).get();
        Assertions.assertEquals(10, findUser.getVersion());
    }

    @Test
    @DisplayName("비관적 락(Write)이 걸린 상태에서는 데이터베이스에서 락을 관리하기 때문에 정상적으로 버전이 증가한다.")
    void PESSIMISTIC_FORCE_INCREMENT_데드락_테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int index = 0; index < 5; index++) {
            final int value = index;
            executorService.submit(() -> {
                try {
                    orderCommandService.orderV4(user.getId(), "Change" + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        User findUser = userJpaRepository.findById(user.getId()).get();
        Assertions.assertEquals(10, findUser.getVersion());
    }

    @Test
    @DisplayName("낙관적 락이 걸린 상태에서 수정을 가하면 Deadlock이 발생해서 충돌한 트랜잭션의 버전이 증가하지 않는다.")
    void OPTIMISTIC_데드락_테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int index = 0; index < 2; index++) {
            final int value = index;
            executorService.submit(() -> {
                try {
                    orderCommandService.orderV1_1(user.getId(), "Change" + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        User findUser = userJpaRepository.findById(user.getId()).get();
        Assertions.assertEquals(1, findUser.getVersion());
    }

    @Test
    @DisplayName("낙관적 락이 걸린 상태에서 수정이 없다면 DeadLock이 걸리지 않으며 충돌이 발생하지 않는다. 단, 이때 버전은 증가하지 않는다.")
    void OPTIMISTIC_READ_테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int index = 0; index < 10; index++) {
            final int value = index;
            executorService.submit(() -> {
                try {
                    orderCommandService.orderV1_2(user.getId(), "Change" + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        User findUser = userJpaRepository.findById(user.getId()).get();
        Assertions.assertEquals(0, findUser.getVersion());
    }

    @Test
    @DisplayName("낙관적 락이 걸린 상태에서 수정을 가하면 Deadlock이 발생해서 버전이 증가하지 않는다.")
    void OPTIMISTIC_FORCE_INCREMENT_데드락_테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int index = 0; index < 10; index++) {
            final int value = index;
            executorService.submit(() -> {
                try {
                    orderCommandService.orderV5(user.getId(), "Change" + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        User findUser = userJpaRepository.findById(user.getId()).get();
        Assertions.assertNotEquals(10, findUser.getVersion());
    }

}
