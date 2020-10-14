package cs.digital.media.feeds.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "nos", url = "${feeds.nos.url}")
public interface FeedsNosClient {

    @RequestMapping(method = RequestMethod.GET)
    Response getItems();

}
