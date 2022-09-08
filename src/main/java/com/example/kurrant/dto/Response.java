package com.example.kurrant.dto;

import com.example.kurrant.model.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Long articleId;
    private String nameKo;
    private LocalDate createdDate;
    private Boolean isPinned;
    private int viewCount;
    private String title;
    private String contentHtml;

    public Response(Article article, String nameKo) {
        this.articleId = article.getArticle_id();
        this.nameKo = nameKo;
        this.createdDate = article.getCreated_date();
        this.isPinned = article.getIs_pinned();
        this.viewCount = article.getView_count();
        this.title = article.getTitle();
        this.contentHtml = article.getContent_html();
    }
}
