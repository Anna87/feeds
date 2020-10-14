package cs.digital.media.feeds.service;

import cs.digital.media.feeds.client.FeedsNosClient;
import cs.digital.media.feeds.dto.request.Item;
import cs.digital.media.feeds.model.Article;
import cs.digital.media.feeds.model.Image;
import cs.digital.media.feeds.repository.ArticleRepository;
import cs.digital.media.feeds.scheduler.WrongContentException;
import feign.FeignException;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final FeedsNosClient feedsNosClient;

    private final ItemMapper itemMapper;

    private final FileStorageService fileStorageService;

    private final ArticleRepository articleRepository;

    private final ImageHelper imageHelper;

    public void getNewPost() {

        final Response response = feedsNosClient.getItems();

        if (200 > response.status() || response.status() > 299) {
            throw FeignException.errorStatus(String.format("Failed with status %s", response.status()), response);
        }

        try (response) {
            final InputStream inputStream = response.body().asInputStream();

            final Item[] items = itemMapper.mapToItem(inputStream);

            for (Item item : items) {
                final Article.ArticleBuilder articleBuilder = Article.builder();

                getImage(item)
                        .ifPresent(articleBuilder::image);

                articleBuilder
                        .title(item.getTitle())
                        .publicationDate(item.getPubDate())
                        .description(item.getDescription());

                articleRepository.save(articleBuilder.build());
            }
        } catch (final IOException e) {
            throw new WrongContentException(e);
        }
    }

    private Optional<Image> getImage(Item item) {
        final Optional<File> fileOptional = imageHelper.downloadFile(item.getEnclosure().getUrl());

        return fileOptional.flatMap(file -> fileStorageService.save(file, item.getEnclosure().getType()));

    }

    public Page<Article> getAll(final Pageable pageable) {
        return articleRepository.findAll(pageable);
    }
}
