package cs.digital.media.feeds.repository;

import cs.digital.media.feeds.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {

    Page<Article> findAll(Pageable pageable);

}
