package cs.digital.media.feeds.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    
    public LocalDateTime convertToLocalDate(final String date) {

        final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

        return LocalDateTime.parse(date, formatter);
    }
}
