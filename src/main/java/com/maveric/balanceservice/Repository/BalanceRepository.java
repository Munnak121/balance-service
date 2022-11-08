package com.maveric.balanceservice.Repository;

import com.maveric.balanceservice.Entity.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends MongoRepository<Balance,String> {
}
