package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final Map<Long, Film> films = new HashMap<>();
    private Long filmId = 1L;

    @GetMapping
    public Collection<Film> findAll() {
        return films.values();
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        validFilm(film);
        film.setId(filmId);
        filmId++;
        films.put(film.getId(), film);
        log.debug("Фильм успешно создан");
        return film;
    }

    @PutMapping
    public Film update(@RequestBody Film newFilm) {
        validFilm(newFilm);
        if (newFilm.getId() == null) {
            throw new ValidationException("Id должен быть указан");
        }
        if (!films.containsKey(newFilm.getId())) {
            throw new ValidationException("id = " + newFilm.getId() + " не найден");
        }
        films.put(newFilm.getId(), newFilm);
        log.debug("Фильм успешно изменен");
        return newFilm;
    }

    private void validFilm(Film film) {
        if (film.getName() == null) {
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDescription().length() >= 200) {
            throw new ValidationException("Максимальная длина описания — 200 символов");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Дата релиза — не раньше 28 декабря 1895 года");
        }
        if (film.getDuration() < 1) {
            throw new ValidationException("Продолжительность фильма должна быть положительным числом");
        }
    }
}
