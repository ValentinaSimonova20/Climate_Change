package com.climatechange.newsapp.service.news;

import com.climatechange.newsapp.integration.api.NewsApi;
import com.climatechange.newsapp.model.NewsData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsApi newsApi;
    @Value("${news.api.key}")
    private String newsApiKey;

    @Override
    public NewsData getNewsDataByQ(String q) {
        return newsApi.getNewsData(q, newsApiKey);
    }

}
