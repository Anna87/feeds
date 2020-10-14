package cs.digital.media.feeds.service;

import cs.digital.media.feeds.model.Image;
import cs.digital.media.feeds.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Optional<Image> getById(long id) {
        return imageRepository.findById(id);
    }
}
