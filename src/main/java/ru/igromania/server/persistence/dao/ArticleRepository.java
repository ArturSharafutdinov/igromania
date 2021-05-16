package ru.igromania.server.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.igromania.server.persistence.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {


}
