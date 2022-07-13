package com.services.myretail.controller;

import com.services.myretail.exceptions.ProductNotFoundException;
import com.services.myretail.models.CurrentPrice;
import com.services.myretail.models.Product;
import com.services.myretail.models.ProductPricing;
import com.services.myretail.repository.ProductPricingRepository;
import com.services.myretail.service.ProductsService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyRetailController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MyRetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService service;

    private Product testProduct;
    private ProductPricing testProductPrice;
    private String updateProductPriceReq;

    @BeforeAll
    public void setup() {
        testProduct  = new Product (12, "test", "12.34");
        testProductPrice = new ProductPricing (12, new CurrentPrice ("1.299", "test"));
        updateProductPriceReq = "{\"id\":12,\"name\":\"test\",\"price\":\"1.299\"}";
      }

    @Test
    public void shouldReturnProduct() throws Exception {
        when(service.getProduct (12)).thenReturn(testProduct);
        MvcResult result = this.mockMvc.perform (get("/myretail/products/{id}", 12))
                .andExpect(status().isOk()).andReturn ();

        assert  (result.getResponse ().getContentAsString ().contains (testProduct.getPrice ()));
    }

    @Test
    public void shouldReturnProductNotFound() throws Exception {
        when(service.getProduct (12)).thenThrow (ProductNotFoundException.class);
        MvcResult result = this.mockMvc.perform (get("/myretail/products/{id}", 12))
                .andExpect(status().isNotFound ()).andReturn ();
    }

    @Test
    public void shouldUpdateProductPrice () throws Exception {
        when (service.updateProductPrice (any())).thenReturn(testProductPrice);
        mockMvc.perform (put ("/myretail/products/{id}", 12)
                .contentType (MediaType.APPLICATION_JSON)
                .content (updateProductPriceReq))
                .andExpect (status ().isOk ())
                .andExpect(result -> assertTrue(result.getResponse ().getContentAsString ().contains ("1.299")));

    }

    @Test
    public void shouldNotUpdateProductPrice_InvalidRequest () throws Exception {
         when (service.updateProductPrice (any ())).thenReturn (null);
         mockMvc.perform (put ("/myretail/products/{id}", 11)
                .contentType (MediaType.APPLICATION_JSON)
                .content (updateProductPriceReq))
                .andExpect (status ().isBadRequest ())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));

    }

    @Test
    public void shouldNotUpdateProductPrice_InvalidProduct () throws Exception {
        when (service.updateProductPrice (any ())).thenThrow (ProductNotFoundException.class);

        mockMvc.perform (put ("/myretail/products/{id}", 12)
                .contentType (MediaType.APPLICATION_JSON)
                .content (updateProductPriceReq))
                .andExpect (status ().isNotFound ())
                .andExpect (result -> assertTrue (result.getResolvedException () instanceof ProductNotFoundException));

    }
}
