package com.climatechange.newsapp.controller;

import com.climatechange.newsapp.model.NewsData;
import com.climatechange.newsapp.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/all")
    public @ResponseBody NewsData getNewsData(@RequestParam String q) {
        return newsService.getNewsDataByQ(q);
    }
}
