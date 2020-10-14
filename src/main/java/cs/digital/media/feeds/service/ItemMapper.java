package cs.digital.media.feeds.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cs.digital.media.feeds.dto.request.Item;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ItemMapper {

    private final ObjectMapper objectMapper;

    public Item[] mapToItem(final InputStream inputStream) throws IOException {

        objectMapper.registerModule(new JavaTimeModule());

        final JSONObject xmlJSONObj = XML.toJSONObject(new String(inputStream.readAllBytes()));

        JsonNode mapped = objectMapper.readTree(xmlJSONObj.toString());
        for (String s : "rss".split("\\.")) {
            mapped =  mapped.findPath(s);
        }

        final JsonNode jsonNode = mapped.get("channel").get("item");
        if (jsonNode.isArray()) {
            return objectMapper.convertValue(jsonNode, Item[].class);
        }

        return new Item[0];
    }

}
