package com.newsag.controller;

import com.newsag.dto.NewsItem;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    private static final String RSS_FEED_URL = "https://timesofindia.indiatimes.com/rssfeedstopstories.cms";
    // TOI Top Stories feed (public)

    @GetMapping("/news")
    public List<NewsItem> getLatestNews() {
        try {
            URL feedUrl = new URL(RSS_FEED_URL);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            return feed.getEntries()
                    .stream()
                    .map(entry -> new NewsItem(
                            entry.getTitle(),
                            entry.getLink(),
                            entry.getDescription() != null ? entry.getDescription().getValue() : "",
                            entry.getPublishedDate()
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Error fetching RSS feed", e);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/api/hello")
    public String hello(){
        return "Hello from the News Aggregation backend micro service";
    }
}
