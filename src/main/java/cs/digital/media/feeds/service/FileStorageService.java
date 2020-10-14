package cs.digital.media.feeds.service;

import cs.digital.media.feeds.model.Image;
import cs.digital.media.feeds.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageService {

    private final ImageRepository imageRepository;

    public Optional<Image> save(final File file, String filetype) {
        byte[] body;

        try {
            body = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            log.warn("Cannot read file {}", file.getAbsolutePath(), e);
            deleteTempFile(file);
            return Optional.empty();
        }

//        final Optional<Image> image = Optional.of(imageRepository.save(Image.builder()
//                .type(filetype)
//                .body(body)
//                .build()));

        deleteTempFile(file);

        return Optional.of(Image.builder()
                .type(filetype)
                .body(body)
                .build());
    }

    private void deleteTempFile(final File file) {
        try {
            file.delete();
        } catch (final SecurityException e) {
            log.warn("Failed to delete temp file {}", file.getAbsolutePath(), e);
        }
    }
}
