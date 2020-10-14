package cs.digital.media.feeds.repository;

import cs.digital.media.feeds.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, String> {
}
