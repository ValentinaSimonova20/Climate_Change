package com.simonova.ecoinformerapp.services.news;

import com.simonova.ecoinformerapp.model.NewsData;
import com.simonova.ecoinformerapp.model.NewsRequest;

public interface NewsService {
    NewsData getAllNews(NewsRequest newsRequest);
}
