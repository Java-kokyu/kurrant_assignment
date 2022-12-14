package com.example.kurrant.service;

import com.example.kurrant.model.Article;
import com.example.kurrant.repository.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService implements ArticleMapper {
    private final ArticleMapper articleMapper;

    @Override
    public int createArticle(Article article) {
        return articleMapper.createArticle(article);
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
    public int plusViewCount(Long articleId) {
        return articleMapper.plusViewCount(articleId);
    }

    @Override
    public int deleteArticle(Long boardId, Long articleId) {
        return articleMapper.deleteArticle(boardId, articleId);
    }

    @Override
    public List<Article> searchArticleByKeyword(String keyword) {
        return articleMapper.searchArticleByKeyword(keyword);
    }

    @Override
    public List<Article> searchArticleByDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return articleMapper.searchArticleByDate(startDateTime, endDateTime);
    }
}
