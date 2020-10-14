package cs.digital.media.feeds.controller;

import cs.digital.media.feeds.controller.response.ArticleResponse;
import cs.digital.media.feeds.converter.ArticleResponseConverter;
import cs.digital.media.feeds.model.Article;
import cs.digital.media.feeds.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleResponseConverter articleResponseConverter;

    private final ArticleService articleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ArticleResponse> getAll(
            @PageableDefault
            @SortDefault.SortDefaults({@SortDefault(sort = "publicationDate",
                    direction = Sort.Direction.DESC)}) final Pageable pageable
    ) {
        final Page<Article> articlePage = articleService.getAll(pageable);

        return new PageImpl<>(
                articleResponseConverter.convertList(articlePage.getContent()),
                pageable,
                articlePage.getTotalElements()
        );
    }
}
