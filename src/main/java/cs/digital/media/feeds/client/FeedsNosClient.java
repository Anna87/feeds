package cs.digital.media.feeds.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        value = "feedsClient",
        url = "http://feeds.nos.nl/nosjournaal")
public interface FeedsNosClient {

    @RequestMapping(method = RequestMethod.GET, value = "?format=xml")
    Response getItems();

}
