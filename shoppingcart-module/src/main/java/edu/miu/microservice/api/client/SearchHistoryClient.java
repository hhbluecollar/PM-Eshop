package edu.miu.microservice.api.client;

import edu.miu.microservice.entity.shoppingcart.pattern.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "eshop-user", qualifier = "searchHistoryClient")
public interface SearchHistoryClient {

    @PostMapping(value = "/ecommerce/v1/user/addSearchHistory")
    void saveSearchHistory(@RequestBody Customer user);
}
