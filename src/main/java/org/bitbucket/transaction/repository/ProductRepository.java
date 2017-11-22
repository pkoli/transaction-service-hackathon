package org.bitbucket.transaction.repository;

import org.bitbucket.transaction.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer>{

    Product findByTransactionTypeAndMerchantName(String transactionType, String merchantName);
}
