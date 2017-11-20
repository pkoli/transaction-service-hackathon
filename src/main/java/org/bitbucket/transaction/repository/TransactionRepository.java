package org.bitbucket.transaction.repository;

import org.bitbucket.transaction.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
}
