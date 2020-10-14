package cs.digital.media.feeds.converter;

import cs.digital.media.feeds.dto.response.ArticleResponse;
import cs.digital.media.feeds.model.Article;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleResponseConverter {

    public ArticleResponse convert(final Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .publicationDate(article.getPublicationDate())
                .imageResponseId(article.getImage().getId())
                .build();
    }

    public List<ArticleResponse> convertList(final List<Article> entities) {
        return entities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
