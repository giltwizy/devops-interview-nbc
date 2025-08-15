package com.crdb.nbctransactions.repository;

import com.crdb.nbctransactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByReference(String id);
}
