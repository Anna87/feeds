package cs.digital.media.feeds.controller.response;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class ArticleResponse {

    String title;

    String description;

    Date publicationDate;

    Long imageId;
}
