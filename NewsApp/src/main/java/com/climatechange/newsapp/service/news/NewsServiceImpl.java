package com.climatechange.newsapp.service.news;

import com.climatechange.newsapp.integration.api.NewsApi;
import com.climatechange.newsapp.model.Article;
import com.climatechange.newsapp.model.NewsData;
import com.climatechange.newsapp.model.NewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsApi newsApi;
    @Value("${news.api.key}")
    private String newsApiKey;

    @Override
    public NewsData getNewsDataByQ(NewsRequest newsRequest) {
        NewsData newsData = newsApi.getNewsData(
                newsRequest.getQ(),
                newsApiKey,
                newsRequest.getPageSize(),
                newsRequest.getPage()
        );
        validateImageUrls(newsData.getArticles());
        return newsData;
    }

    /**
     * if the imageUrl not accessible then clear it
     * @param articleList articles in which we check image urls
     */
    private void validateImageUrls(List<Article> articleList) {
        articleList.forEach(article -> {
            try {
                ImageIO.read(new URL(article.getUrlToImage()));
            } catch (IOException e) {
                article.setUrlToImage("");
            }
        });
    }
}
