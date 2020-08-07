package edu.miu.eshop.product.api.client;

import edu.miu.eshop.product.entity.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "eshop-user", qualifier = "searchHistoryClient")
public interface SearchHistoryClient {

    @PostMapping(value = "/ecommerce/v1/user/addSearchHistory")
    void saveSearchHistory(@RequestBody Customer user);
}
