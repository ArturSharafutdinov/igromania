package ru.igromania.server.services.newsDb;

import ru.igromania.server.persistence.domain.Article;
import ru.igromania.server.persistence.domain.Author;
import ru.igromania.server.persistence.domain.Section;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface NewsDbService {

    void saveAuthorToDb(String authorName);

    void saveSectionToDb(String sectionName);

    boolean contains(String name, List<Article> articlesFromDb);

    void saveArticlesToDb();

    List<Article> getAllArticles();

    List<Author> getAllAuthors();

    List<Section> getAllSections();

    void parse() throws IOException, ParseException;

    void updateAllArticlesViews();

}
