DROP TABLE IF EXISTS `cms__article`;
DROP TABLE IF EXISTS `cms__board`;

CREATE TABLE `cms__board`(
                             `board_id` SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '게시판 PK',
                             `name_ko` VARCHAR(16) NOT NULL COMMENT '게시판 명',
                             PRIMARY KEY(`board_id`),
                             UNIQUE KEY(`name_ko`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '게시판';

CREATE TABLE `cms__article`(
                               `article_id` BIGINT(20) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '게시글 PK',
                               `board_id` SMALLINT UNSIGNED NOT NULL COMMENT '게시판 FK',
                               `created_datetime` TIMESTAMP(6) NOT NULL DEFAULT NOW(6) COMMENT '생성날짜',
                               `is_pinned` BIT(1) NOT NULL DEFAULT 0 COMMENT '게시판 내 고정여부',
                               `view_count` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '게시판 내 고정여부',
                               `title` VARCHAR(32) NOT NULL COMMENT '제목',
                               `content_html` TEXT NOT NULL COMMENT '본문',
                               `content_string` TEXT NOT NULL COMMENT '본문 내용만 저장, 검색용이다.',
                               PRIMARY KEY(`article_id`),
                               FULLTEXT KEY (`title`, `content_string`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '게시글';

ALTER TABLE `cms__article` ADD CONSTRAINT `a_attach_board` FOREIGN KEY (`board_id`) REFERENCES `cms__board` (`board_id`) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO cms__board
VALUES (1, '자유게시판'), (2, '질문게시판'), (3, '공유게시판')