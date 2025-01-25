package ru.yandex.practicum.filmorate.storage.memory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.service.UserFriend;
import ru.yandex.practicum.filmorate.storage.UserFriendStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InMemoryUserFriendStorage implements UserFriendStorage {
    List<UserFriend> userFriends = new ArrayList<>();

    @Override
    public List<UserFriend> getFriends(Long userId) {
        log.info("Get user {} friends", userId);
        return userFriends.stream()
                .filter(uf -> Objects.equals(uf.getUserId(), userId))
                .collect(Collectors.toList());
    }

    @Override
    public UserFriend addFriend(Long userId, Long friendId) {
        UserFriend userFriend = UserFriend.builder()
                .id((long) userFriends.size())
                .userId(userId)
                .friendId(friendId).build();
        userFriends.add(userFriend);
        log.info("Add user {} friend {}", userId, friendId);
        return userFriend;
    }

    @Override
    public void removeFriend(Long userId, Long friendId) {
        UserFriend userFriend = userFriends.stream()
                .filter(uf ->
                        Objects.equals(uf.getUserId(), userId) &&
                                Objects.equals(uf.getFriendId(), friendId)
                ).findFirst().orElse(null);

        if (userFriend == null) {
            return;
        }
        log.info("Remove user {} friend {}", userId, friendId);
        userFriends.remove(userFriend);
    }

    @Override
    public boolean containsFriend(Long user, Long friend) {
        return userFriends.stream().anyMatch(ufl ->
                Objects.equals(ufl.getUserId(), user) && Objects.equals(ufl.getFriendId(), friend));
    }
}
