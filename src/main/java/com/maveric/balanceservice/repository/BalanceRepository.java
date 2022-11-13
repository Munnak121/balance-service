package com.maveric.balanceservice.repository;

import com.maveric.balanceservice.entity.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends MongoRepository<Balance,String> {
}