package cs.digital.media.feeds.dto.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Enclosure {
    private int length;
    private String type;
    private String url;
}
