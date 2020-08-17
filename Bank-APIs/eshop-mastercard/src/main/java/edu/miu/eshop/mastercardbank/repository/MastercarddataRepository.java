package edu.miu.eshop.mastercardbank.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.miu.eshop.mastercardbank.domain.Mastercarddata;


@Repository
public interface MastercarddataRepository extends MongoRepository<Mastercarddata, BigInteger> {
	public Mastercarddata findMastercarddataBycardHolder(String cardname);
	
}
