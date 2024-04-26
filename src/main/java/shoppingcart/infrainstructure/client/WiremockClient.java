package shoppingcart.infrainstructure.client;

import shoppingcart.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import shoppingcart.infrainstructure.config.Config;

import java.util.List;

@FeignClient(name="client", url="${integration.wiremock.host}", configuration = Config.class)
public interface WiremockClient {

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    List<Product> getAllProducts();

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    Product getProduct(@PathVariable("productId") String productId);


}
