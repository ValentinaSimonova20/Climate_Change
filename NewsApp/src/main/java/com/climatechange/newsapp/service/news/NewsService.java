package com.climatechange.newsapp.service.news;

import com.climatechange.newsapp.model.NewsData;

public interface NewsService {
    NewsData getNewsDataByQ(String q);
}
