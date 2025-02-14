package ru.yandex.practicum.filmorate.storage.memory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.utils.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {
    Map<Long, Film> films = new HashMap<>();

    @Override
    public List<Film> getAll() {
        log.info("Get all films");
        return new ArrayList<>(films.values());
    }

    @Override
    public Film get(long id) {
        log.info("Get film {}", id);
        return films.get(id);
    }

    @Override
    public Film add(Film film) {
        film.setId(MapUtils.getNextId(films));
        films.put(film.getId(), film);
        log.info("Add film: {}", film);
        return film;
    }

    @Override
    public Film update(Film film) {
        log.info("Update film: {}", film);
        return films.put(film.getId(), film);
    }

    @Override
    public boolean contains(long id) {
        return films.containsKey(id);
    }
}