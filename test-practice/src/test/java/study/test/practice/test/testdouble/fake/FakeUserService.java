package study.test.practice.test.testdouble.fake;

import study.test.practice.domain.user.entity.User;
import study.test.practice.web.user.application.UserService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FakeUserService implements UserService {

    private final Map<Long, User> fakeMemberRepository = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public User save(User user) {
        Long newId = sequence.incrementAndGet();
        User newUser = new User(newId, user.getName());
        fakeMemberRepository.put(newId, newUser);
        return newUser;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
