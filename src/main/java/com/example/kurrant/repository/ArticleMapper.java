package com.example.kurrant.repository;

import com.example.kurrant.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {
    int createArticle(@Param("article") Article request);

    List<Article> getArticleList(Long boardId);

    String getBoardName(Long boardId);

    Article retrieveArticle(Long boardId, Long articleId);

    int plusViewCount(Long articleId);

    int deleteArticle(Long boardId, Long articleId);

    List<Article> searchArticleByKeyword(String keyword);

    List<Article> searchArticleByDate(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
