package cs.digital.media.feeds.controller;

import cs.digital.media.feeds.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping(value = "/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable("id") final Long id) {

        return imageService.getById(id)
                .map(value ->
                        ResponseEntity.ok()
                                .contentType(MediaType.parseMediaType(value.getType()))
                                .<Resource>body(new ByteArrayResource(value.getBody(), "")))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
