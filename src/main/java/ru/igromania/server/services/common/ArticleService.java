package ru.igromania.server.services.common;

import ru.igromania.server.persistence.domain.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAll();

    void save(Article article);

    Article getById(Long id);

}
