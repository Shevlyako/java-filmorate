package ru.yandex.practicum.filmorate.exception;

public class FilmNotFoundException extends ElementNotFoundException {
    public FilmNotFoundException(Long filmId) {
        super(String.format("Фильм с ID: %d не найден!", filmId));
    }
}