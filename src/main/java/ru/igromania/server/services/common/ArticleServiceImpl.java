package ru.igromania.server.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.igromania.server.persistence.dao.ArticleRepository;
import ru.igromania.server.persistence.domain.Article;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Transactional
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Transactional
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Transactional
    public Article getById(Long id) {
        return articleRepository.findById(id).get();
    }
}
