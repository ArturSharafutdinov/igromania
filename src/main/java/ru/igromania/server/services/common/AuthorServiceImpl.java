package ru.igromania.server.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.igromania.server.persistence.dao.AuthorRepository;
import ru.igromania.server.persistence.domain.Author;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Transactional
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Transactional
    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }

}