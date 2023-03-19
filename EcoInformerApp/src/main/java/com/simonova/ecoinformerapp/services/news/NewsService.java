package com.simonova.ecoinformerapp.services.news;

import com.simonova.ecoinformerapp.model.NewsData;

public interface NewsService {
    NewsData getAllNews(String q);
}
