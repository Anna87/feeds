package cs.digital.media.feeds.service;

import cs.digital.media.feeds.model.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    public Optional<Image> retrieveFile(final String url, final String fileType) {
        return downloadFile(url)
                .map(file -> buildImage(file, fileType))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public Optional<File> downloadFile(final String urlString) {
        try {
            final String randomFileName = UUID.randomUUID().toString();
            final File tempFile = new File(randomFileName);

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

    private Optional<Image> buildImage(final File file, String fileType) {
        byte[] body;

        try {
            body = FileUtils.readFileToByteArray(file);
            deleteTempFile(file);
            return Optional.of(Image.builder()
                    .type(fileType)
                    .body(body)
                    .build());
        } catch (IOException e) {
            log.warn("Cannot read file {}", file.getAbsolutePath(), e);
            deleteTempFile(file);
        }
            return Optional.empty();
    }

    private void deleteTempFile(final File file) {
        try {
            file.delete();
        } catch (final SecurityException e) {
            log.warn("Failed to delete temp file {}", file.getAbsolutePath(), e);
        }
    }
}
