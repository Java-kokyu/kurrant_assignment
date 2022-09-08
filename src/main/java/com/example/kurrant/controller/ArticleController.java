package com.example.kurrant.controller;

import com.example.kurrant.dto.Request;
import com.example.kurrant.dto.Response;
import com.example.kurrant.dto.SqlRequest;
import com.example.kurrant.model.Article;
import com.example.kurrant.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    //1. 게시물 생성
    @PostMapping("/api/boards/{id}/articles")
    public void createArticle(@PathVariable Long id, @RequestBody Request request) {
        SqlRequest sqlRequest = new SqlRequest(id, request.getTitle(), request.getContent());
        Article article = new Article(sqlRequest);
        articleService.createArticle(article);
    }

    //2. 게시물 상세 조회
    @GetMapping("/api/boards/{boardId}/articles/{articleId}")
    public Response retrieveArticle(@PathVariable Long boardId, @PathVariable Long articleId) {
        articleService.plusViewCount(articleId);
        Article article = articleService.retrieveArticle(boardId, articleId);
        String boardName = articleService.getBoardName(article.getBoard_id());
        return new Response(article, boardName);
    }

    //3. 게시물 목록 조회
    @GetMapping("/api/boards/{boardId}/articles")
    public List<Response> getArticleList(@PathVariable Long boardId) {
        List<Article> articles = articleService.getArticleList(boardId);
        List<Response> responses = new ArrayList<>();
        for (Article article : articles) {
            String boardName = articleService.getBoardName(article.getBoard_id());
            Response response = new Response(article, boardName);
            responses.add(response);
        }
        return responses;
    }

    //4. 게시글 삭제
    @DeleteMapping("/api/boards/{boardId}/articles/{articleId}")
    public void deleteArticle(@PathVariable Long boardId, @PathVariable Long articleId) {
        articleService.deleteArticle(boardId, articleId);
    }

    @GetMapping("/api/boards/{boardId}")
    public String getBoardName(@PathVariable Long boardId) {
        String boardName = articleService.getBoardName(boardId);
        return boardName;
    }


}
