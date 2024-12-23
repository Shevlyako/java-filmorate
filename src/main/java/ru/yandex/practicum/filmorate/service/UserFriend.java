package ru.yandex.practicum.filmorate.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFriend {
    public Long id;
    public Long userId;
    public Long friendId;
}
