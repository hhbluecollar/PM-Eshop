package edu.miu.eshop.bankapi.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.miu.eshop.bankapi.domain.TransactionHist;


@Repository
public interface TransactionHistRepository extends MongoRepository<TransactionHist, String> {
	
}
