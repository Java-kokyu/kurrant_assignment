package com.example.kurrant.model;

import com.example.kurrant.dto.SqlRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Long article_id;
    private Long board_id;
    private LocalDate created_date;
    private Boolean is_pinned;
    private int view_count;
    private String title;
    private String content_html;
    private String content_string;

    public Article(SqlRequest sqlRequest) {
        this.board_id = sqlRequest.getBoardId();
        this.title = sqlRequest.getTitle();
        this.content_html = sqlRequest.getContent();
        this.content_string = sqlRequest.getContent();
    }
}
