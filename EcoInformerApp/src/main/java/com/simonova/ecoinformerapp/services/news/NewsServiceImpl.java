package com.simonova.ecoinformerapp.services.news;

import com.simonova.ecoinformerapp.integration.api.NewsApi;
import com.simonova.ecoinformerapp.model.NewsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsApi newsApi;

    @Override
    public NewsData getAllNews(String q) {
        return newsApi.getNewsData(q);
    }
}
