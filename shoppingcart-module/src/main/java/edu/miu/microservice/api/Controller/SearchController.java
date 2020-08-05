package edu.miu.microservice.api.Controller;

import edu.miu.microservice.entity.search.Product;
import edu.miu.microservice.entity.search.SearchHistory;
import edu.miu.microservice.entity.shoppingcart.Order;
import edu.miu.microservice.repository.OrderRepository;
import edu.miu.microservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SearchService searchService;
    @Autowired
    private HttpServletRequest request;

    private String remoteIPAddress;

    private SearchHistory searchHistory;
    private Double maxPrice = Double.MAX_VALUE;

    @PostMapping("")
    public ArrayList<Product> search(@RequestParam("productName") String productName, @RequestParam("productCategory") String productCategory,
                                     @RequestParam("manufacturer") String manufacturer, @RequestParam("minProductPrice") Double minProductPrice,
                                     @RequestParam("maxProductPrice") Double maxProductPrice, @RequestBody String customerId){
            /**
             * Save search history
             */
            searchHistory = new SearchHistory();
            searchHistory.setCustomerId(customerId);
            saveHistory(productName);

            /**
            * Search
            */
        return searchService.searchType(productName,productCategory,manufacturer, maxProductPrice,minProductPrice);
    }

    @PostMapping("/test/{id}")
    public String test(@PathVariable int id){
        orderRepository.save(new Order( ));
        return "TEST WORKS with id : " + id;
    }

    public void  saveHistory( String searchWord ){

        if(request!=null) {
            /**
             * The first 'if' when the user uses proxies
             */
            remoteIPAddress = request.getHeader("X-FORWARDED-FOR");
            if (remoteIPAddress == null || "".equals(remoteIPAddress)) {
                remoteIPAddress = request.getRemoteAddr();
            }
            searchHistory.setSearchWord(searchWord);
            searchHistory.setCustomerIP(remoteIPAddress);
            searchHistory.setSearchDate(LocalDateTime.now());

            searchService.saveSearchHistory(searchHistory);
            //connect to user microservice and save it
        }
    }
}