package cs.digital.media.feeds.converter;

import cs.digital.media.feeds.controller.response.ArticleResponse;
import cs.digital.media.feeds.model.Article;
import cs.digital.media.feeds.model.Image;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ArticleResponseConverter {

    public ArticleResponse convert(final Article article) {
        return ArticleResponse.builder()
                .title(article.getTitle())
                .description(article.getDescription())
                .publicationDate(article.getPublicationDate())
                .imageId(Optional.ofNullable(article.getImage()).map(Image::getId).orElse(null))
                .build();
    }

    public List<ArticleResponse> convertList(final List<Article> entities) {
        return entities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
