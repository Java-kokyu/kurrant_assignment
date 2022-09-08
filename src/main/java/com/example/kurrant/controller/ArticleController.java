package com.example.kurrant.controller;

import com.example.kurrant.dto.Request;
import com.example.kurrant.dto.Response;
import com.example.kurrant.dto.SqlRequest;
import com.example.kurrant.model.Article;
import com.example.kurrant.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        return getResponses(articles);
    }

    //4. 게시글 삭제
    @DeleteMapping("/api/boards/{boardId}/articles/{articleId}")
    public void deleteArticle(@PathVariable Long boardId, @PathVariable Long articleId) {
        articleService.deleteArticle(boardId, articleId);
    }

    //5. 게시글 조회
    @GetMapping("/api/articles/search")
    public List<Response> searchArticleByKeyword(@RequestParam(required = false, defaultValue = "") String keyword) {
        List<Article> articles = articleService.searchArticleByKeyword(keyword);
        return getResponses(articles);
    }

    //5. 게시글 조회
    @GetMapping("/api/articles/date")
    public List<Response> searchArticleByDate(@RequestParam String startDate,
                                              @RequestParam String endDate) {
        LocalDateTime startDateTime = LocalDate.of(Integer.parseInt(startDate.substring(0, 4)),
                                                Integer.parseInt(startDate.substring(4, 6)),
                                                Integer.parseInt(startDate.substring(6, 8)))
                                                .atTime(LocalTime.MIDNIGHT);
        LocalDateTime endDateTime = LocalDate.of(Integer.parseInt(endDate.substring(0, 4)),
                                                Integer.parseInt(endDate.substring(4, 6)),
                                                Integer.parseInt(endDate.substring(6, 8)))
                                                .atTime(LocalTime.MAX);
        List<Article> articles = articleService.searchArticleByDate(startDateTime, endDateTime);
        return getResponses(articles);
    }

    public List<Response> getResponses(List<Article> articles) {
        List<Response> responses = new ArrayList<>();
        for (Article article : articles) {
            String boardName = articleService.getBoardName(article.getBoard_id());
            Response response = new Response(article, boardName);
            responses.add(response);
        }
        return responses;
    }


}
