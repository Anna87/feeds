package cs.digital.media.feeds.dto.response;

import lombok.Builder;
import lombok.Value;


@Value
@Builder(toBuilder = true)
public class ImageResponse {
    long id;

    String type;

    byte[] body;
}
