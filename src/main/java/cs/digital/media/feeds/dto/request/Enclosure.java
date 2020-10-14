package cs.digital.media.feeds.dto.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Enclosure {
    int length;
    String type;
    String url;
}
