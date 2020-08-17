package edu.miu.eshop.bankapi.Repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.miu.eshop.bankapi.domain.Card;

@Repository
public interface CardRepository extends MongoRepository<Card, String>
{

}
