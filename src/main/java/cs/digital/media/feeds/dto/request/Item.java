package cs.digital.media.feeds.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cs.digital.media.feeds.util.CustomDateDeserializer;
import lombok.Builder;
import lombok.Value;

import javax.persistence.Column;
import java.util.Date;
import java.util.LinkedHashMap;

@Value
@Builder
public class Item {
    String title;

    @Column(columnDefinition = "TEXT")
    String description;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    Date pubDate;

    @JsonProperty("feedburner:origLink")
    String feedburnel;

    String link;

    LinkedHashMap guid;

    Enclosure enclosure;
}
