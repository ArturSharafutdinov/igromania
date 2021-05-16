package ru.igromania.server.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.igromania.server.persistence.domain.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);
}
