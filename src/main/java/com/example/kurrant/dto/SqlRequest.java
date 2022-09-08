package com.example.kurrant.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SqlRequest {
    private Long boardId;
    private String title;
    private String content;

    public SqlRequest(Long boardId, Request request) {
        this.boardId = boardId;
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}

