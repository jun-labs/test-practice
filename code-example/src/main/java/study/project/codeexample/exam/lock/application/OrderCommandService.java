package study.project.codeexample.exam.lock.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import study.project.codeexample.domain.order.entity.Order;
import study.project.codeexample.domain.order.infrastructure.OrderJpaRepository;
import study.project.codeexample.domain.user.entity.User;
import study.project.codeexample.web.user.application.UserQueryService;

@Service
@RequiredArgsConstructor
public class OrderCommandService {

    private final UserQueryService userQueryService;
    private final OrderJpaRepository orderJpaRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void orderV1(Long userId, String name) throws InterruptedException {
        User findUser = userQueryService.findUserByIdOptimisticLock(userId, name);
        orderJpaRepository.save(new Order(findUser));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void orderV2(Long userId, String name) throws InterruptedException {
        User findUser = userQueryService.findUserByIdPessimisticLockRead(userId, name);
        orderJpaRepository.save(new Order(findUser));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void orderV3(Long userId, String name) throws InterruptedException {
        User findUser = userQueryService.findUserByIdPessimisticLockWrite(userId, name);
        orderJpaRepository.save(new Order(findUser));
    }
}
