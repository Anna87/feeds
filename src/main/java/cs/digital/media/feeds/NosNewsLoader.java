package cs.digital.media.feeds;

import cs.digital.media.feeds.client.FeedsNosClient;
import feign.FeignException;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class NosNewsLoader {
    
    private final FeedsNosClient feedsNosClient;
    
    public Optional<Document> loadHtml(){
        final Response response = feedsNosClient.getItems();

        if (200 > response.status() || response.status() > 299) {
            throw FeignException.errorStatus(String.format("Failed with status %s", response.status()), response);
        }


        try (response) {
            final InputStream inputStream = response.body().asInputStream();
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            return Optional.of(builder.parse(inputStream, StandardCharsets.UTF_8.name()));

        } catch (final IOException | ParserConfigurationException | SAXException e) {
            return Optional.empty();
        }
    }
}
