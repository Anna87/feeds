package cs.digital.media.feeds.converter;

import cs.digital.media.feeds.dto.request.Item;
import cs.digital.media.feeds.model.Article;
import cs.digital.media.feeds.service.ImageHelper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleConverter {


    ImageHelper imageHelper;

    public Article convert(final Item item) {

//        return Article.builder()
//                .title(item.getTitle())
//                .description(item.getDescription())
//                .publicationDate(LocalDateTime.parse(item.getPubDate()))
//                .image()
//                .build();
        return null;

    }

    public List<Article> convertList(final Item[] entities) {
        return Arrays.stream(entities)
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
