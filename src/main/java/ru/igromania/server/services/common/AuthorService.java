package ru.igromania.server.services.common;

import ru.igromania.server.persistence.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void save(Author author);

    Author findByName(String name);

}
