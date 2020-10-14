package cs.digital.media.feeds.controller;

import cs.digital.media.feeds.model.Article;
import cs.digital.media.feeds.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping(path="/add")
    public void add() {
        Article article = Article.builder().title("anyStr").build();
        articleRepository.save(article);
    }
}
