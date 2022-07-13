package com.services.myretail.repository;

import com.services.myretail.models.CurrentPrice;
import com.services.myretail.models.ProductPricing;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class ProductPricingRepositoryTest {

    @Autowired
    ProductPricingRepository pricingRepo;

    @BeforeAll
    public void setUp () {
        ProductPricing testPP = new ProductPricing (12, new CurrentPrice ("12.34", "test"));
        pricingRepo.save (testPP);
    }

    @AfterAll
    public void teardown() {
        pricingRepo.deleteAll ();
    }

    @Test
    public void shouldBeNotEmpty () {
        assertThat (pricingRepo.findAll ()).isNotEmpty ();
    }

    @Test
    public void shouldBeNull () {
        assertThat (pricingRepo.findById (10)).isNull ();
    }

}
