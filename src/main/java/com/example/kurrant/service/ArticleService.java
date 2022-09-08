package com.example.kurrant.service;

import com.example.kurrant.model.Article;
import com.example.kurrant.repository.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService implements ArticleMapper {
    private final ArticleMapper articleMapper;

    @Override
    public void createArticle(Article article) {
        articleMapper.createArticle(article);
    }

    @Override
    public List<Article> getArticleList(Long boardId) {
        return articleMapper.getArticleList(boardId);
    }


    @Override
    public String getBoardName(Long boardId) {
        return articleMapper.getBoardName(boardId);
    }

    @Override
    public Article retrieveArticle(Long boardId, Long articleId) {
        return articleMapper.retrieveArticle(boardId, articleId);
    }

    @Override
    public void plusViewCount(Long articleId) {
        articleMapper.plusViewCount(articleId);
    }

    @Override
    public void deleteArticle(Long boardId, Long articleId) {
        articleMapper.deleteArticle(boardId, articleId);
    }
}
