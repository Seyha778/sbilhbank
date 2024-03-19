package kh.com.sbilhbank.external.service;

// UserServiceImpl.java
import kh.com.sbilhbank.external.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final List<User> users = new ArrayList<>();
    private Long userIdCounter = 1L;

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User createUser(User user) {
        user.setId(userIdCounter++);
        users.add(user);
        return user;
    }

}
