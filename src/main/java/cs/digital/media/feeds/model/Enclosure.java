package cs.digital.media.feeds.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Enclosure {

    String type;

    String url;

}
