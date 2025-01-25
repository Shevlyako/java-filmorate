package ru.yandex.practicum.filmorate.exception;

public class UserNotFoundException extends ElementNotFoundException {
    public UserNotFoundException(Long userId) {
        super(String.format("Пользователь с ID: %d не найден!", userId));
    }
}
