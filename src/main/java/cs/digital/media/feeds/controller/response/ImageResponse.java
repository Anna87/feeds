package cs.digital.media.feeds.controller.response;

import lombok.Builder;
import lombok.Value;


@Value
@Builder(toBuilder = true)
public class ImageResponse {

    Long id;

    String type;

    byte[] body;
}
