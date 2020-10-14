package cs.digital.media.feeds.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Service
@Slf4j
public class ImageHelper {
    public Optional<File> downloadFile(final String urlString) {
        try {
            final File tempFile = new File("FILE_NAME");
            FileUtils.copyURLToFile(
                    new URL(urlString),
                    tempFile,
                    10000,
                    60000);
            return Optional.of(tempFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
