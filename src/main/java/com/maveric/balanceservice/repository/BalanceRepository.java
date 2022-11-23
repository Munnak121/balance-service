package com.maveric.balanceservice.repository;

import com.maveric.balanceservice.entity.Balance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends MongoRepository<Balance,String> {
    Page<Balance> findAllByAccountId(String accountId, Pageable pageable);
    List<Balance> findAllByAccountId(String accountId);
    @Override
    Optional<Balance> findById(String s);



}
