package study.project.codeexample.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.project.codeexample.domain.user.entity.User;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserByIdOptimisticLock(@Param("id") Long id);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserByIdOptimisticLockWithForceIncrement(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserByIdPessimisticLockRead(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserByIdPessimisticLockWrite(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserByIdPessimisticLockWithForceIncrement(@Param("id") Long id);
}
