package cs.digital.media.feeds.service;

import cs.digital.media.feeds.model.Article;
import cs.digital.media.feeds.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final FileService fileService;

    public Page<Article> getAll(final Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    private Article save(final Article article) {
        return articleRepository.save(article);
    }

    public boolean existsByTitleAndPublicationDate(final String title, final Date pubDate) {
        return articleRepository.existsByTitleAndPublicationDate(title, pubDate);
    }

    public void save(final String title, Date pubDate, final String description, final String url, final String fileType) {
        if (existsByTitleAndPublicationDate(title, pubDate)) {
            return;
        }

        final Article.ArticleBuilder articleBuilder = Article.builder();

        fileService.retrieveFile(url, fileType)
                .ifPresent(articleBuilder::image);

        articleBuilder
                .title(title)
                .publicationDate(pubDate)
                .description(description);

        try {
            save(articleBuilder.build());
        } catch (ConstraintViolationException e) {
            log.warn("Can not add new item because item already exist");
        }

    }
}
