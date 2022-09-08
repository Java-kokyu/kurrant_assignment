package com.example.kurrant;

import com.example.kurrant.dto.Request;
import com.example.kurrant.dto.Response;
import com.example.kurrant.dto.SqlRequest;
import com.example.kurrant.model.Article;
import com.example.kurrant.repository.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class KurrantApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Transactional
    @Test
    @DisplayName("게시물 생성")
    void insertTest() {
        Request request = new Request("테스트", "테스트 게시글");
        SqlRequest sqlRequest = new SqlRequest(1L, request);
        Article article = new Article(sqlRequest);

        int result = articleMapper.createArticle(article);
        assertEquals(1, result);
    }

    @Transactional
    @Test
    @DisplayName("게시물 상세조회")
    void retrieveTest() {
        String str = "테스트입니다.";
        Request request = new Request(str, "테스트 게시글");
        SqlRequest sqlRequest = new SqlRequest(1L, request);
        Article article = new Article(sqlRequest);

        articleMapper.createArticle(article);
        List<Article> responses = articleMapper.searchArticleByKeyword(str);
        Article article2 = responses.get(0);

        assertEquals(request.getTitle(), article2.getTitle());
        assertEquals(request.getContent(), article2.getContent_html());
        assertEquals(request.getContent(), article2.getContent_string());
        assertEquals(sqlRequest.getBoardId(), article2.getBoard_id());

    }

    @Transactional
    @Test
    @DisplayName("게시물 목록 조회")
    void getArticleList() {
        int oldListSize = articleMapper.getArticleList(1L).size();

        Request request = new Request("목록 조회 테스트", "테스트 게시글");
        SqlRequest sqlRequest = new SqlRequest(1L, request);
        Article article = new Article(sqlRequest);
        articleMapper.createArticle(article);

        int recentListSize = articleMapper.getArticleList(1L).size();

        assertEquals(recentListSize, oldListSize + 1);

    }

    @Transactional
    @Test
    @DisplayName("게시물 삭제")
    void deleteArticle() {
        Request request = new Request("단위 테스트1", "테스트 게시글1");
        SqlRequest sqlRequest = new SqlRequest(1L, request);
        Article article = new Article(sqlRequest);
        articleMapper.createArticle(article);

        Request request2 = new Request("단위 테스트2", "테스트 게시글2");
        SqlRequest sqlRequest2 = new SqlRequest(1L, request2);
        Article article2 = new Article(sqlRequest2);
        articleMapper.createArticle(article2);

        Request request3 = new Request("단위 테스트3", "테스트 게시글3");
        SqlRequest sqlRequest3 = new SqlRequest(1L, request3);
        Article article3 = new Article(sqlRequest3);
        articleMapper.createArticle(article3);

        int oldListSize = articleMapper.getArticleList(1L).size();
        List<Article> articles = articleMapper.searchArticleByKeyword("단위");

        articleMapper.deleteArticle(1L, articles.get(0).getArticle_id());
        articleMapper.deleteArticle(1L, articles.get(1).getArticle_id());

        int recentListSize = articleMapper.getArticleList(1L).size();
        assertEquals(recentListSize, oldListSize - 2);

        articleMapper.deleteArticle(1L, articles.get(2).getArticle_id());
        recentListSize = articleMapper.getArticleList(1L).size();
        assertEquals(recentListSize, oldListSize - 3);

    }

    @Transactional
    @Test
    @DisplayName("게시물 검색 (단어)")
    void searchKeywordTest() {
        String str = "테스트입니다.";
        Request request = new Request(str, "테스트 게시글");
        SqlRequest sqlRequest = new SqlRequest(1L, request);
        Article article = new Article(sqlRequest);

        articleMapper.createArticle(article);
        List<Article> responses = articleMapper.searchArticleByKeyword(str);

        assertTrue(responses.size() > 0);

    }

    @Transactional
    @Test
    @DisplayName("특정 기간 게시물 검색")
    void searchDateTest() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minAfter = LocalDateTime.now().plusMinutes(3);
        int oldListSize = articleMapper.searchArticleByDate(now, minAfter).size();

        Request request = new Request("단위 테스트1", "테스트 게시글1");
        SqlRequest sqlRequest = new SqlRequest(1L, request);
        Article article = new Article(sqlRequest);
        articleMapper.createArticle(article);

        Request request2 = new Request("단위 테스트2", "테스트 게시글2");
        SqlRequest sqlRequest2 = new SqlRequest(1L, request2);
        Article article2 = new Article(sqlRequest2);
        articleMapper.createArticle(article2);

        Request request3 = new Request("단위 테스트3", "테스트 게시글3");
        SqlRequest sqlRequest3 = new SqlRequest(1L, request3);
        Article article3 = new Article(sqlRequest3);
        articleMapper.createArticle(article3);

        int recentListSize = articleMapper.searchArticleByDate(now, minAfter).size();
        assertEquals(recentListSize, oldListSize + 3);

    }

}
