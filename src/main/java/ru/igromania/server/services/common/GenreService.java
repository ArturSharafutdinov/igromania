package ru.igromania.server.services.common;

import ru.igromania.server.persistence.domain.Genre;

import java.util.List;

public interface GenreService {

    void save(Genre genre);

    List<Genre> getAll();

    Genre findByName(String name);

}
