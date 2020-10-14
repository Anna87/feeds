package cs.digital.media.feeds.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class ArticleResponse {

    long id;

    String title;

    String description;

    Date publicationDate;

    long imageResponseId;
}
