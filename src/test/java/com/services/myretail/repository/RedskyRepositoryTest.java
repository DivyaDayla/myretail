package com.services.myretail.repository;

import com.services.myretail.models.RedskyProduct;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedskyRepositoryTest {

    private RedskyRepository redskyRepository;
    private int VALID_PRODUCT_ID = 13860428;
    private int INVALID_PRODUCT_ID = 13860429;

    RestTemplate restTemplate;

    @BeforeEach
    void setUp () {
        redskyRepository = new RedskyRepository ();
        redskyRepository.restTemplate = new RestTemplate ();
    }

    @AfterEach
    void tearDown () {
    }

    @Test
    public void test_ProductNotFound()  {
        RedskyProduct redskyProduct = redskyRepository.getRedskyProduct (INVALID_PRODUCT_ID);
        assertNull (redskyProduct);
    }

    @Test
    public void test_ProductFound () {
        RedskyProduct redskyProduct = redskyRepository.getRedskyProduct (VALID_PRODUCT_ID);
        assertNotNull (redskyProduct);
    }

    @Test
    public void test_ProductFound_Title () {
        RedskyProduct redskyProduct = redskyRepository.getRedskyProduct (VALID_PRODUCT_ID);
        String actualTitle = redskyProduct.getItem ().getProduct_description ().getTitle ();

        assertNotNull (redskyProduct);
        assertEquals ("The Big Lebowski (Blu-ray)", actualTitle);

    }
}