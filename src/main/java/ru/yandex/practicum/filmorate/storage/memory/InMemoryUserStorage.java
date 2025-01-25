package ru.yandex.practicum.filmorate.storage.memory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.utils.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    Map<Long, User> users = new HashMap<>();

    @Override
    public List<User> getAll() {
        log.info("Get all users");
        return new ArrayList<>(users.values());
    }

    @Override
    public User get(long id) {
        log.info("Get user: {}", id);
        return users.get(id);
    }

    @Override
    public User add(User user) {
        user.setId(MapUtils.getNextId(users));
        users.put(user.getId(), user);
        log.info("Add user: {}", user);
        return user;
    }

    @Override
    public User update(User user) {
        log.info("Update user: {}", user);
        return users.put(user.getId(), user);
    }

    @Override
    public boolean contains(long userId) {
        return users.containsKey(userId);
    }
}
