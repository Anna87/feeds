package cs.digital.media.feeds.scheduler;

import cs.digital.media.feeds.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArticleScheduler {

    @Autowired
    ArticleService articleService;

    @Scheduled(fixedRateString = "${feeds.scheduler.rate}")
    public void getNewArticles() {

        articleService.getNewPost();


    }

}
