package com.example.kurrant;

import com.example.kurrant.repository.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KurrantApplicationTests {
    @Autowired
    ArticleMapper articleMapper;

    @Test
    void contextLoads() {
    }

}
