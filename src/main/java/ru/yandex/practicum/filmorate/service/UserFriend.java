package ru.yandex.practicum.filmorate.service;

import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.model.FriendshipStatus;

@Data
@Builder
public class UserFriend {
    public Long id;
    public Long userId;
    public Long friendId;
    public FriendshipStatus friendshipStatus;
}
