package ru.igromania.server.services.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.igromania.server.persistence.dao.GenreRepository;
import ru.igromania.server.persistence.domain.Genre;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    GenreRepository genreRepository;

    @Transactional
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Transactional
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Transactional
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }

}
