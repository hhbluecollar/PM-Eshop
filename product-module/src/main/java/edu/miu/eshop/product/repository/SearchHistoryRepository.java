package edu.miu.eshop.product.repository;

import edu.miu.eshop.product.entity.SearchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchHistoryRepository extends MongoRepository<SearchHistory, String>  {

}
