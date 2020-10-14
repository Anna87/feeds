package cs.digital.media.feeds.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cs.digital.media.feeds.util.CustomDateDeserializer;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class Item {

    String title;

    String description;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    Date pubDate;

    Enclosure enclosure;
}
