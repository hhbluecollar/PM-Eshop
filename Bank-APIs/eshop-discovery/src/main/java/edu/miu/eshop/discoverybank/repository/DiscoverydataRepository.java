package edu.miu.eshop.discoverybank.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.miu.eshop.discoverybank.domain.Discoverydata;


@Repository
public interface DiscoverydataRepository extends MongoRepository<Discoverydata, BigInteger> {
	public Discoverydata findDiscoverydataBycardHolder(String cardname);
	
}
