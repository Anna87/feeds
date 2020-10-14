package cs.digital.media.feeds.scheduler;

import cs.digital.media.feeds.loader.FeedLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class ArticleScheduler {

    private final Set<FeedLoader> feedLoaders;

    @Scheduled(cron = "${feeds.scheduler.cron}")
    public void loadNews() {
        feedLoaders.forEach(feedLoader -> {
            try {
                feedLoader.loadData();
            } catch (final Exception e) {
                log.error("Failed to load feed data from {}", feedLoader.getName(), e);
            }
        });
    }

}
