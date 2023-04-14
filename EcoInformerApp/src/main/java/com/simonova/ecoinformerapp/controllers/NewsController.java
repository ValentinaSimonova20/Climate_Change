package com.simonova.ecoinformerapp.controllers;

import com.simonova.ecoinformerapp.model.NewsData;
import com.simonova.ecoinformerapp.model.NewsRequest;
import com.simonova.ecoinformerapp.services.news.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class NewsController {

    NewsService newsService;

    @GetMapping("/news")
    public String getEcoNewsInfoPage(NewsRequest request, Model model) {
        NewsData newsData = newsService.getAllNews(request);
        model.addAttribute("articles", newsData.getArticles());
        model.addAttribute("totalPages", newsData.getTotalResults()/5);
        model.addAttribute("currentPage", request.getPage());
        return "news-info";
    }
}
