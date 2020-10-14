package cs.digital.media.feeds.scheduler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WrongContentException extends RuntimeException {

    public WrongContentException(final Exception e) {
        super(e);
    }

}
