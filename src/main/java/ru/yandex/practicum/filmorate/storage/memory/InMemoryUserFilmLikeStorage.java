package ru.yandex.practicum.filmorate.storage.memory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.service.UserFilmLike;
import ru.yandex.practicum.filmorate.storage.UserFilmLikeStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InMemoryUserFilmLikeStorage implements UserFilmLikeStorage {
    List<UserFilmLike> userFilmLikes = new ArrayList<>();

    @Override
    public List<UserFilmLike> getAll() {
        log.info("Get all films");
        return userFilmLikes;
    }

    @Override
    public List<UserFilmLike> getLikesByUserId(long userId) {
        log.info("Get likes from user {}", userId);
        return userFilmLikes.stream()
                .filter(ufl -> Objects.equals(ufl.getUserId(), userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserFilmLike> getLikesByFilmId(long filmId) {
        log.info("Get likes film {}", filmId);
        return userFilmLikes.stream()
                .filter(ufl -> Objects.equals(ufl.getFilmId(), filmId))
                .collect(Collectors.toList());
    }

    @Override
    public UserFilmLike addLike(long userId, long filmId) {
        UserFilmLike userFilmLike = UserFilmLike.builder()
                .id((long) userFilmLikes.size())
                .filmId(filmId)
                .userId(userId).build();

        userFilmLikes.add(userFilmLike);
        log.info("Add like to film {} from user {}", filmId, userId);
        return userFilmLike;
    }

    @Override
    public void removeLike(long userId, long filmId) {
        UserFilmLike userFilmLike = userFilmLikes.stream()
                .filter(ufl ->
                        Objects.equals(ufl.getUserId(), userId) &&
                                Objects.equals(ufl.getFilmId(), filmId)
                ).findFirst().orElse(null);

        if (userFilmLike == null) {
            return;
        }
        log.info("Remove like from film {} with user {}", filmId, userId);
        userFilmLikes.remove(userFilmLike);
    }

    @Override
    public boolean containsLike(long userId, long filmId) {
        return userFilmLikes.stream().anyMatch(ufl ->
                Objects.equals(ufl.getUserId(), userId) && Objects.equals(ufl.getFilmId(), filmId));
    }
}
