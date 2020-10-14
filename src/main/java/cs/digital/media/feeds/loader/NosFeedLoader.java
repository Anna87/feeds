package cs.digital.media.feeds.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.digital.media.feeds.client.FeedsNosClient;
import cs.digital.media.feeds.model.Item;
import cs.digital.media.feeds.scheduler.WrongContentException;
import cs.digital.media.feeds.service.ArticleService;
import feign.FeignException;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class NosFeedLoader implements FeedLoader {

    private static final String NOS_FEED_LOADER = "Nos feed loader";

    private final ObjectMapper objectMapper;

    private final FeedsNosClient feedsNosClient;

    private final ArticleService articleService;

    @Override
    public String getName() {
        return NOS_FEED_LOADER;
    }

    public void loadData() {

        final Response response = feedsNosClient.getItems();

        if (200 > response.status() || response.status() > 299) {
            throw FeignException.errorStatus(String.format("Failed with status %s", response.status()), response);
        }

        processLoadedData(response);
    }

    private void processLoadedData(final Response response) {
        try (response) {
            final InputStream inputStream = response.body().asInputStream();
            final Item[] items = mapToItem(inputStream);

            for (Item item : items) {
                articleService.save(
                        item.getTitle(),
                        item.getPubDate(),
                        item.getDescription(),
                        item.getEnclosure().getUrl(),
                        item.getEnclosure().getType());
            }
        } catch (IOException e) {
            throw new WrongContentException(e);
        }
    }

    private Item[] mapToItem(final InputStream inputStream) throws IOException {

        final JSONObject xmlJSONObj = XML.toJSONObject(new String(inputStream.readAllBytes()));

        final JsonNode mapped = objectMapper.readTree(xmlJSONObj.toString());

        final JsonNode jsonNode = mapped.get("rss").get("channel").get("item");

        if (jsonNode.isArray()) {
            return objectMapper.convertValue(jsonNode, Item[].class);
        }

        return new Item[0];
    }

}
