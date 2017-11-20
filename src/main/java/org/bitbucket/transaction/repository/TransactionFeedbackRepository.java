package org.bitbucket.transaction.repository;

import org.bitbucket.transaction.entity.TransactionFeedback;
import org.springframework.data.repository.CrudRepository;

public interface TransactionFeedbackRepository extends CrudRepository<TransactionFeedback, Integer>{
}
