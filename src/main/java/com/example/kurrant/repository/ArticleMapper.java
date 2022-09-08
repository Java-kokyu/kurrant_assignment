package com.example.kurrant.repository;

import com.example.kurrant.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {
    void createArticle(@Param("article") Article request);
    List<Article> getArticleList(Long boardId);

    String getBoardName(Long boardId);

    Article retrieveArticle(Long boardId, Long articleId);

    void plusViewCount(Long articleId);

    void deleteArticle(Long boardId, Long articleId);
}
