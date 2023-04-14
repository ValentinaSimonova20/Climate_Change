package com.climatechange.newsapp.service.news;

import com.climatechange.newsapp.model.NewsData;
import com.climatechange.newsapp.model.NewsRequest;

public interface NewsService {
    NewsData getNewsDataByQ(NewsRequest newsRequest);
}
