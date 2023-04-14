package com.climatechange.newsapp.controller;

import com.climatechange.newsapp.model.NewsData;
import com.climatechange.newsapp.model.NewsRequest;
import com.climatechange.newsapp.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Slf4j
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/all")
    public @ResponseBody NewsData getNewsData(NewsRequest newsRequest) {
        log.info("news request starts");
        return newsService.getNewsDataByQ(newsRequest);
    }
}
