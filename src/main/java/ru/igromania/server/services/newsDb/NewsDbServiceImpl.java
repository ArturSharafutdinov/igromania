package ru.igromania.server.services.newsDb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.igromania.server.parsers.IgromaniaNewsParser;
import ru.igromania.server.persistence.domain.Article;
import ru.igromania.server.persistence.domain.Author;
import ru.igromania.server.persistence.domain.Section;
import ru.igromania.server.persistence.dto.ArticleDto;
import ru.igromania.server.services.common.ArticleService;
import ru.igromania.server.services.common.AuthorService;
import ru.igromania.server.services.common.SectionService;
import ru.igromania.server.services.mappers.ArticleMapper;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class NewsDbServiceImpl implements NewsDbService {

    // List of articles from newsParser
    private static List<ArticleDto> articles;

    @Autowired
    private AuthorService authorService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private ArticleService articleService;


    public void parse() throws IOException, ParseException {
        articles = new IgromaniaNewsParser().getInfoFromNews();
    }

    public void updateAllArticlesViews() {
        List<Article> articlesToUpdate = articleService.getAll();
        for (Article article : articlesToUpdate) {
            int newViews = new IgromaniaNewsParser().getViewsFromArticleByUrl(article.getLink());
            article.setViews(newViews);
            articleService.save(article);
        }

    }

    // Save authors from articleDto field author
    public void saveAuthorToDb(String authorName) {
        List<Author> authors = authorService.getAll();
        if (!authors.isEmpty()) {
            boolean isHave = false;
            for (Author author : authors) {
                if (author.getName().equals(authorName)) {
                    isHave = true;
                }
            }
            if (!isHave) {
                Author newAuthor = new Author();
                newAuthor.setName(authorName);
                authorService.save(newAuthor);
            }

        } else {
            Author newAuthor = new Author();
            newAuthor.setName(authorName);
            authorService.save(newAuthor);
        }

    }

    // Save sections from articleDto field author
    public void saveSectionToDb(String sectionName) {
        List<Section> sections = sectionService.getAll();
        if (!sections.isEmpty()) {
            boolean isHave = false;
            for (Section section : sections) {
                if (section.getName().equals(sectionName)) {
                    isHave = true;
                }
            }
            if (!isHave) {
                Section newSection = new Section();
                newSection.setName(sectionName);
                sectionService.save(newSection);
            }

        } else {
            Section newSection = new Section();
            newSection.setName(sectionName);
            sectionService.save(newSection);
        }

    }

    // Check if db.Article contains article with parameter name
    public boolean contains(String name, List<Article> articlesFromDb) {
        for (Article article : articlesFromDb) {
            if (article.getName().equals(name))
                return true;
        }
        return false;
    }

    public void saveArticlesToDb() {
        ArticleMapper mapper = new ArticleMapper();
        List<Article> articlesFromDb = articleService.getAll();
        if (!articlesFromDb.isEmpty()) {

            boolean isHave = false; // Проверка на наличие в бд

            for (ArticleDto articleDto : articles) {
                if (contains(articleDto.getName(), articlesFromDb)) { // Полностью проходимся по бд
                    isHave = true;
                }
                if (!isHave) {
                    saveAuthorToDb(articleDto.getAuthor());
                    saveSectionToDb(articleDto.getType());
                    Article article = mapper.mapToEntity(articleDto);
                    Author author = authorService.findByName(articleDto.getAuthor());
                    article.setAuthor(author);

                    Section section = sectionService.findByName(articleDto.getType());
                    article.setSection(section);
                    articleService.save(article);
                }

            }
        } else {
            for (ArticleDto articleDto : articles) {
                saveAuthorToDb(articleDto.getAuthor());
                saveSectionToDb(articleDto.getType());

                Article article = mapper.mapToEntity(articleDto);

                Author author = authorService.findByName(articleDto.getAuthor());
                article.setAuthor(author);

                Section section = sectionService.findByName(articleDto.getType());
                article.setSection(section);

                articleService.save(article);
            }
        }
    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> articlesFromDb = articleService.getAll();
        return articlesFromDb;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authorsFromDb = authorService.getAll();
        return authorsFromDb;
    }

    @Override
    public List<Section> getAllSections() {
        List<Section> sectionsFromDb = sectionService.getAll();
        return sectionsFromDb;
    }
//
//
//    // Print all info from database section related to news
//    public void showAllInfo() {
//        System.out.println("Articles: ");
//        List<Article> articlesFromDb = articleRepository.findAll();
//
//        for (Article article : articlesFromDb) {
//            System.out.println(article);
//        }
//
//        System.out.println();
//        System.out.println("Authors: ");
//        List<Author> authorsFromDb = authorRepository.findAll();
//        for (Author author : authorsFromDb) {
//            System.out.println(author);
//        }
//
//        System.out.println();
//        System.out.println("Sections: ");
//        List<Section> sectionsFromDb = sectionRepository.findAll();
//        for (Section section : sectionsFromDb) {
//            System.out.println(section);
//        }
//    }


//    public static void main(String[] args) throws IOException, ParseException {
//   IgromaniaNewsParser parser = new IgromaniaNewsParser();
//      parser.getInfoFromNews();
////      //  IgromaniaNewsParser.showAllArticles();
//   NewsService newsService = new NewsService(parser.getAllArticles());
// newsService.saveArticleToDb();
//       // newsService.showAllInfo();
//    }

}
