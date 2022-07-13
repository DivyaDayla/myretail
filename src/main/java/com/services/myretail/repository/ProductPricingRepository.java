package com.services.myretail.repository;

import com.services.myretail.models.ProductPricing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPricingRepository extends MongoRepository<ProductPricing, Integer> {

    ProductPricing findById(int id);

}
