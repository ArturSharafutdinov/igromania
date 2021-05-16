package ru.igromania.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.igromania.server.services.common.ArticleServiceImpl;
import ru.igromania.server.persistence.dto.ArticleDto;
import ru.igromania.server.services.mappers.ArticleMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    @Autowired
    private ArticleServiceImpl articleServiceImpl;

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/news")
    public List<ArticleDto> allNews(){
        return articleServiceImpl.getAll().stream().map(article -> articleMapper.mapToDto(article)).collect(Collectors.toList());
    }

    @GetMapping("/news/{id}")
    public ArticleDto singleArticle(@PathVariable Long id){
       return articleMapper.mapToDto(articleServiceImpl.getById(id));
    }

}
