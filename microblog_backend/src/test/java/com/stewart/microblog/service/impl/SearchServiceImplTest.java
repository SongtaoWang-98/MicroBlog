package com.stewart.microblog.service.impl;

import com.stewart.microblog.service.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchServiceImplTest {
    @Resource
    SearchService searchService;

    @Test
    void searchByUsername() {
        System.out.println(searchService.searchByUsername("songtao_wang@qq.com"));
    }
}